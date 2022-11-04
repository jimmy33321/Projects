
require 'yaml'
require_relative 'WebPage.rb'


f = File.open("out.txt", 'w+')


def loadData(fileName = "dataTest.yml")
	data = YAML.load_file(fileName)
	return data
end
def loadIndex(fileName = "dataTest.yml")
	data = Marshal.load(File.binread(fileName))
	return data
end

def read_data(file_name)
	file = File.open(file_name,"r")
	object = eval(file.gets)
	file.close()
	return object
end
	
stopWords = read_data("stopWords")
documentIndex = loadIndex("documentIndex.dat")
invertedIndex = loadIndex("invertedIndex.dat")


# check that the user gave us correct command line parameters
#abort "Command line should have at least 1 parameters." if ARGV.size<1

#mode = ARGV[0]
keywordList = ARGV[0..ARGV.size]

#The invertedIndex looks like this...
#invertedIndex[keyWord] --> {address --> #of times it is on the page}

#The documentIndex looks like this...
#documentIndex[address] --> "1.yml"



#this is how to use this....
#
#you enter a string into the invertedIndex. It must me lower case and removed if it is a stop word.
#		ex.		invertedIndex["string"]
#
#This gives you another ruby Hash. 
#The keys of this hash are the hyperlinks and the values are how many times that "string"
#appears on that page
#
#Now that you have that, you can sort it by it's value, reverse it so it isn't backwords, then take the top 10 |address, result| 
#pairs and return them. My implementation goes a little further  allowing you to re-open the saved data page that the crawler/indexer
#processed earlier to give you tons of information about the page.
#To do this you just take the hyperlink that we got earlier and give it to the documentIndex to load the Webpage object
#		ex.		page = documentIndex[address]
# then you can access whatever feature about the page you want. If you check the Webpage.rb you can see all the attributes that the
#Webpage Class has

# count = 10
# prospectiveHits = invertedIndex["lincoln"].sort_by {|_key, value| value}.reverse
# puts("Total Results: #{prospectiveHits.size}")
# prospectiveHits.each do |address, result|
	# if count > 0
		# page = loadData("pages/" + documentIndex[address])
		# puts("Address:\t#{page.address}\nTitle:\t#{page.title}\nRank:\t#{page.rank}\nQuote:\t#{page.lines[page.index["lincoln"].docPositions.sample]}\n\n")
	# end
	# count -= 1
# end


#stop the keywords
stoppedKeywords = Array.new
keywordList.each do |word|
	#puts(word)
	if not stopWords.include?(word)
		#puts("true")
		stoppedKeywords.push(word)
	end
end
#puts(stoppedKeywords)
keywordSampler = Array.new
keywordSampler = stoppedKeywords


#if theres more than one
if stoppedKeywords.size >= 2
	prospectiveHits = invertedIndex[stoppedKeywords.pop]
	#find the intersection of all of the keys
	stoppedKeywords.each do |keyword|
		prospectiveHits.keep_if {|k, v| invertedIndex[keyword].key?(k)}
	end
	puts("Total Results: #{prospectiveHits.size}")
	count = 10
	#puts(prospectiveHits)
	hits = prospectiveHits.sort_by {|_key, value| value}.reverse
	hits.each do |address, result|
		if count > 0
			page = loadData("pages/" + documentIndex[address])
                  #ios = IO.new STDOUT.fileno
			f.write("Address:\t#{page.address}\nTitle:\t#{page.title}\nRank:\t#{page.rank}\nQuote:\t#{page.lines[page.index[keywordSampler.sample].docPositions.sample]}\n\n")
                  #ios.close
		end
	count -= 1
	end
# if theres only one valid input word
elsif stoppedKeywords.size == 1
	keyword = stoppedKeywords.pop
	count = 10
	prospectiveHits = invertedIndex[keyword].sort_by {|_key, value| value}.reverse
	puts("Total Results: #{prospectiveHits.size}")
	prospectiveHits.each do |address, result|
		if count > 0
			page = loadData("pages/" + documentIndex[address])
                  #ios = IO.new STDOUT.fileno
	
                  f.write("Address:\t#{page.address}\nTitle:\t#{page.title}\nRank:\t#{page.rank}\nQuote:\t#{page.lines[page.index[keyword].docPositions.sample]}\n\n")
                 # ios.close
		end
	count -= 1
	end
	#if there wasn't any usefull keywords
else
	puts "not enough valid input... try again"
end

f.close()