#Nolan Roach

require 'rubygems'
require 'nokogiri'
require 'open-uri'
require 'mechanize'

require_relative 'Word.rb'

class WebPage
	
	def self.read_data(file_name)
		file = File.open(file_name,"r")
		object = eval(file.gets)
		file.close()
		return object
	end
	
	@@stopWords = WebPage.read_data("stopWords")
	
	#instance variables
	attr_reader :page
	attr_reader :address
	attr_reader :title
	attr_reader :links
	attr_reader :inboundLinks
	attr_reader :rawText
	attr_reader :lines
	attr_reader :index
	attr_reader :rank
	attr_reader :lineCount
	attr_reader :wordCount
	
	#initializer: must take a hyperlink
	def initialize(address, parent=NIL, parentRank=NIL)
        @page = Nokogiri::HTML(open(address)) do |config|
			config.strict.noblanks
		end
		@address = address
		@inboundLinks = Array.new
		@lines = Array.new
		@index = Hash.new
		@lineCount = 0
		@wordCount = 0
		
		if parent == NIL 
			#puts("no parent")
			@rank = 0.25
		else 
			inboundLinks.push(parent)
			@rank = 0.25 + parentRank
		end
		@title = @page.css('title')[0].text
		self.getLinks()
		@rawText = @page.css('body', 'p').text
		self.processLines()
    end

	# #class functions
	
	def getLinks()
		@links = Array.new
		agent = Mechanize.new
		page = agent.get(@address)
		page.links_with(:href => /^https?/).each do |link|
			@links.push(link.href)
		end
	end
	
	# def getLinks()
		# #for some reason have to clean these
		# tempLinks = @page.css('a').map {|link| link.attribute('href').to_s}.uniq.sort.delete_if {|href| href.empty?}
		# @links = Array.new
		 # tempLinks.each do |element|
			 # if element =~ /[#]/
				  # #do nothing
			 # elsif element =~ /(\/)/
				 # if element =~ /(\/\/)/
					# #don't want these
				 # else 
					# @links.push(@address + element[1..element.size])
				 # end
			 # else
				# @links.push(element)
			# end
		# end
	# end
	
	def processLines()
		@rawText.each_line do |line|
			#puts("LINE COUNT #{@lineCount}")
			@lines.push(line)
			words = line.strip.split(/\W+/)
			words.each do |word|
			#puts("\tWORD COUNT #{@wordCount}")
				w = word.downcase
				if not @@stopWords.include?(w) and not w  =~ /[0-9]/
					if not @index.has_key?(w)
						@index[w] = Word.new(w)
					end
					if not @index[w].docPositions.include?(@lineCount)
						@index[w].docPositions.push(@lineCount)
					end
					@index[w].addFreq()
				end
				@wordCount += 1
			end
			@lineCount += 1
		end
	end
	
	def updateRank(value)
		@rank += value
	end
	
	def to_s
		"---------------Web Page---------------\nURL:\t#{@address}\nTitle:\t#{@title}\nRank:\t#{@rank}"
	end
end