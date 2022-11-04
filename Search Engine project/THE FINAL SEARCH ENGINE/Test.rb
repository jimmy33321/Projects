require_relative 'WebPage.rb'
require_relative 'Word.rb'
require_relative 'Crawler.rb'

require 'mechanize'

#this is just a test module

def createDB()
	#doing it like this prevents major memory lock up
	#siteList = ["https://en.wikipedia.org/wiki/Mathematics","https://en.wikipedia.org/wiki/Science","https://en.wikipedia.org/wiki/History","https://en.wikipedia.org/wiki/Language","https://en.wikipedia.org/wiki/United_States","http://www.cnn.com/","http://www.foxnews.com/","https://www.reddit.com/","http://www.gamasutra.com/","https://en.wikipedia.org/wiki/Computer","http://www.amazon.com/"]
	siteList = ["https://en.wikipedia.org/wiki/Language","https://en.wikipedia.org/wiki/United_States","http://www.cnn.com/","http://www.foxnews.com/","https://www.reddit.com/","http://www.gamasutra.com/","https://en.wikipedia.org/wiki/Computer","http://www.amazon.com/"]
	siteList.each do |site|
		c = Crawler.new(50, site)
		c.crawlPages()
	end
end

def testCrawler()
	#since no start page is defined it defaults to IU
	#c = Crawler.new(50, "https://en.wikipedia.org/wiki/Main_Page/")
	c = Crawler.new(15, "https://www.indiana.edu/")
	puts(c)
	c.hello()
	puts(c.currentHTMLPage.page.class)
	puts(c.currentHTMLPage.to_s)
	#c.savePage()
	#puts(c.currentHTMLPage.links)
	#puts(c.currentHTMLPage.lines)
	#puts(c.currentHTMLPage.rawText)
	#c.getPage("https://www.wikipedia.org/" , c.currentHTMLPage.address, (c.currentHTMLPage.rank / c.currentHTMLPage.links.size))
	#puts(c.currentHTMLPage.to_s)
	#puts(c.currentHTMLPage.index)
	#testLoad = Crawler.loadCrawledPage()
	puts(c.pageLimit.to_s)
	c.crawlPages()
end

def testGetLinks()
	agent = Mechanize.new
	page = agent.get("https://www.indiana.edu/")
	page.links_with(:href => /^https?/).each do |link|
		puts (link.href)
	end
end

def testSave()
	file = File.new("test/" + "page1", "w+")
	#file = File.open(currentHTMLPage.address, "w+")
	file.puts("just some stuff")
	file.close
end

def testWebPage()
	p = WebPage.new("http://www.cnn.com/")
	puts(p)
end

def testWord()
	w = Word.new("running")
	puts(w)
end

def testHash()
	a = Hash.new
	a ["x"] = 1
	a ["y"] = 2
	a ["z"] = 3
	a ["a"] = Hash.new
	
	b = a
	
	a ["t"] = -1
	
	puts(b)
	
end

def load_stopwords_file(file = "stop.txt") 
    stop_words = Hash.new(0)
    file = File.open(file, "r")
    file.readlines.each do |word|
      stop_words[word.chomp] = 1
    end

    file.close
    write_data("stopWords", stop_words)
end

def testSavedData(fileName = "documentIndex.yml")
		data = YAML.load_file(fileName)
		
		
		file = File.new("documentIndex.dat", "w+")
		serializedData = Marshal.dump(data)
		file.write(serializedData)
		file.close
		
		d = Marshal.restore(File.binread("documentIndex.dat"))
		puts("done")
end

def testMech()
	mechanize = Mechanize.new

	page = mechanize.get('http://www.cnn.com')

	puts page.at('body').text.strip.split(/\W+/)
end


#load_stopwords_file()
#testGetLinks()
#testWord()
#testWebPage()
#testHash()
#testSave()
#testMech()
#testSavedData()

testCrawler()
#createDB()