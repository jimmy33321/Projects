
#! /l/ruby-2.2.2/bin/ruby

require 'cgi'
require 'mechanize'

cgi = CGI.new("html4")

#input values
query = cgi['user_input'].to_i

output = `ruby search.rb #{query}`

cgi.out{
  cgi.html{
    cgi.head{ "\n"+cgi.title{"Jangwon's page title"} } +
    cgi.body{  "\n"+cgi.h1{"This is a test heading!"} +
      cgi.br+"Output of search engine: #{output}"
#      cgi.br+"Current time: #{time}" +
#      cgi.br+"A Random number: #{rand_num}" +
#      cgi.br+"Sum of two values: #{sum}"
#      cgi.br+"Target HTML: #{target}" +
#      cgi.br+ "#{html}"
    }
  }
}