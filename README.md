# evil_array_of_colors

Few things to consider:
-We should probably rename variable b (BufferedImage) in Filter class to img. 
  That being more explicit and also rgb has letter b
-The pixel class throws ColorException if a value of argb is less than 0 or more than 255.
  There are two ways I see of dealing with such values:
    1) If value ends up being over 255, just set it to 255, if value is < 0, just set it to 0
    2) For < 0, use absolute value, while for over 255 use (mod 256).
        I like this way in a sense that it gives some variability (for example in AddNoize filter)
-Creating classes like Filter (named FilterA, FilterB, e.g) for better organization (?)

      
Thoughts?

Ok! This program takes an image and applies some filters, if you will, and you get something else! How simple!
