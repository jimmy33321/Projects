#Nolan Roach

require 'fast-stemmer'

class Word
	#attr_reader :
	attr_reader :word
	attr_reader :stem
	attr_reader :docFrequency
	attr_reader :docPositions
	
	#initializer
	def initialize(string)
		@word = string
		stemWord()
		@docFrequency = 1.0
		@docPositions = Array.new
    end
	
	def stemWord()
		@stem = Stemmer::stem_word(@word)
	end
	
	def addFreq()
		@docFrequency += 1.0
	end
	
	def to_s
		"#{@word}:\n\tstem:#{@stem}\n\tdocFrequency: #{@docFrequency}\n\tdocPositions: #{@docPositions}\n"
	end
end