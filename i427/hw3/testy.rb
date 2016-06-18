require 'nokogiri'

def read_html_file(filename)

  file = File.open(filename)
  html_code = Nokogiri::HTML(file)
  file.close

  return html_code.to_s
end


# function that takes the *name of an html file stored on disk*, and returns a \
#  of tokens (words) in that file.
#
def find_tokens(filename)
  
  myfile = read_html_file(filename)
  myfile.each do |line|
words = line.split(/[\s+,!?$'";*{}:().@#%0-9]+/)

    print words
  end

end


  w = File.open("index.dat", "r")
  holdy2 = w.readlines()
  wooter = "83"
  w.close()
  
  i = 0
  n = 0
  p = 0
  match = "no"

  if wooter[0] == holdy2[83][0]
    match = "yes"
   
  end

print holdy2.size
print "\n \n"
print wooter.size

  while i < holdy2.size

    while n < wooter.size

        if  wooter[n] == holdy2[i][n]
          p += 1
          n += 1
        else

          n += 1
        end

    end


    if w == wooter.size
      match = "yes"
      n += 1
    end

    if match == "yes"
      url = holdy2[i]
      url = url[wooter.size + 5 .. holdy2[i].size - 1]
    end

    n = 0
    i += 1
    p = 0
  end
  print match

