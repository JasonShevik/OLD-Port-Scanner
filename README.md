# OLD-Port-Scanner
I made this program around 2011 when I was 16 years old.  

This was a very ambitious program that was supposed to allow a user to do any of 3 different port scanning methods (TCP, SYN, and UDP).  
I remember abandoning the project because I was told online that Java did not allow me to create custom packets (which the SYN scan required)  

I "finished" the TCP scans, but upon revisiting the program, it looks like it throws NullPointerException when you try scanning specific ports, and ArrayIndexOutOfBoundsException when scanning all. I believe that despite these errors, it does still tell you the open ports when scanning all ports.

The program is multithreaded as well.
