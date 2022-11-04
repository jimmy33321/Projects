#!/usr/bin/ruby
# I427 Fall 2015, Assignment 4
#   Code authors: Jangwon Lee
#   
#   based on skeleton code by D Crandall

require 'fast_stemmer'

# This function writes out a hash or an array (list) to a file.
#  You can modify this function if you want, but it should already work as-is.
# 
# write_data("file1",my_hash)
# 
def write_data(filename, data)
  file = File.open(filename, "w")
  file.puts(data)
  file.close
end

# This function reads in a hash or an array (list) from a file produced by write_file().
#  You can modify this function if you want, but it should already work as-is.
# 
# my_list=read_data("file1")
# my_hash=read_data("file2")
def read_data(file_name)
  file = File.open(file_name,"r")
  object = eval(file.gets)
  file.close()
  return object
end

#
# You'll likely need other functions. Add them here!
#

# function that takes the name of a file and loads in the stop words from the file.
#  You could return a list from this function, but a hash might be easier and more efficient.
#  (Why? Hint: think about how you'll use the stop words.)
#
def load_stopwords_file(file) 
    stop_words = Hash.new(0)
    file = File.open(file, "r")
    file.readlines.each do |word|
      stop_words[word.chomp] = 1
    end

    file.close
    return stop_words
end

# function that takes a list of tokens, and a list (or hash) of stop words,
#  and returns a new list with all of the stop words removed
#
def remove_stop_tokens(tokens, stop_words)
  tokens_without_stop = Array.new

  tokens.each do |token|
    unless stop_words.include? token
      tokens_without_stop.push(token)
    end
  end

  return tokens_without_stop
end

# function that takes a list of tokens, runs a stemmer on each token,
#  and then returns a new list with the stems
#
def stem_tokens(tokens)
  stem_tokens = Array.new

  tokens.each do |token|
    stem_tokens.push(Stemmer.stem_word(token))
  end
  
  return stem_tokens
end

def find_hitlist(mode, query, invindex)
  hit_list = Array.new
  hit_hash = Hash.new 0
  temp_list = Array.new
  first = 0

  query.each do |term|
    #puts term
    if invindex.has_key?(term)
      #puts invindex[term][1].keys.inspect
      if (first == 0)
        hit_list |= invindex[term][1].keys
        hit_list.each {|doc| hit_hash[doc] += 1}
        first = 1
      else
        if (mode == "or")
          hit_list |= invindex[term][1].keys
        elsif (mode == "and")
          hit_list &= invindex[term][1].keys
        elsif (mode == "most")
          temp_list = invindex[term][1].keys
          temp_list.each {|doc| hit_hash[doc] += 1}
          #puts temp_list.inspect
        end
      end
    else
      hit_list = []
    end
  end

  if (mode == "most")
    #initizlie hit_list
    hit_list = []
    hit_hash.each {|k, v| hit_list.push(k) if v > (query.length / 2)}
  end

  return hit_list
end

#################################################
# Main program. We expect the user to run the program like this:
#
#   ./retrieve.rb mode kw1 kw2 kw3 .. kwn
#

# check that the user gave us correct command line parameters
abort "Command line should have at least 2 parameters." if ARGV.size<2

mode = ARGV[0]
keyword_list = ARGV[1..ARGV.size]

# read in the index file produced by the crawler from Assignment 2 (mapping URLs to filenames).
docindex=read_data("doc.dat")

# read in the inverted index produced by the indexer. 
invindex=read_data("invindex.dat")

# Add your code here!

# read in list of stopwords from file
stopwords = load_stopwords_file("stop.txt")

#puts keyword_list.inspect

# Step (1) Stem and stop the query terms
query_term = stem_tokens(keyword_list)
#puts query_term.inspect

clean_query = remove_stop_tokens(query_term, stopwords)
#puts clean_query.inspect

# Step (2) Use the inverted index file to find the hit list
hit_list = find_hitlist(mode, clean_query, invindex)
#puts hit_list.inspect
#
#
# Step (3) For each page in the hit list,
# display the URL and the title of the HTML page
num_of_doc = hit_list.length

if (num_of_doc == 0)
  print "No documents contained these query terms..\n"
else 
  hit_list.each do |page|
    page_title = docindex[page][1]
    page_url = docindex[page][2]
    print "URL: #{page_url} Title: #{page_title} \n"
  end
end

# Step (4) Display the total number of documents 
# and the total number of hits
print "Total number of document: #{docindex.keys.length} \n"
print "Total number of hits: #{num_of_doc} \n"
