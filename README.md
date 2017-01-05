<h1>Project "Phoneutria"</h1>

<h2>What is a Web Crawler?</h2>


A Web crawler is an Internet bot which systematically browses the World Wide Web, typically for the purpose of Web indexing (web spidering).

Web search engines and some other sites use Web crawling or spidering software to update their web content or indices of others sites' web content. Web crawlers can copy all the pages they visit for later processing by a search engine which indexes the downloaded pages so the users can search much more efficiently.

Crawlers consume resources on the systems they visit and often visit sites without tacit approval. Issues of schedule, load, and "politeness" come into play when large collections of pages are accessed. Mechanisms exist for public sites not wishing to be crawled to make this known to the crawling agent. For instance, including a robots.txt file can request bots to index only parts of a website, or nothing at all.

As the number of pages on the internet is extremely large, even the largest crawlers fall short of making a complete index. For that reason search engines were bad at giving relevant search results in the early years of the World Wide Web, before the year 2000. This is improved greatly by modern search engines, nowadays very good results are given instantly.

Crawlers can validate hyperlinks and HTML code. They can also be used for web scraping.


<h2><b>Features</b></h2>

<h4>Supported features : </h4>
* Infinite layers.
* Robot Meta Tags.
* E-mail Notifications containing : 
 * Scanned Urls.
 * Elapsed Time.
 * Number of Total Links.
 * Number of Scanned Layers.
* SQL Database.
* Directory Tree structure for HTML Files.
* Update Database every 24 Hours with "fresh" Links.
* Safe keeping E-mail, Database Credentials in an external Txt File. 
* Checks Links' validity via checking HTTP Server Response Message.

<h4>Extensions Excluded : </h4>
* .jpeg
* .jpg
* .pdf
* .gif
* .css
* .png
* .mp3
* .mp4
* .js
* .zip
* .gz
* Download links ( "://dl." )

<h4>Thread support : </h4>
* Simultaneous 3 URL scan.
* Sleep for 1,5 seconds to avoid getting thrown out of the Host-Server.

<h2>How to Configure "Phoneutria"</h2>
<p>
Phoneutria is fully written in [Java (v. 8+)](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) and its project is fully managed by [Maven (v. 3.3.9)](https://maven.apache.org/download.cgi).

<h4>Dependencies : </h4>

Download :
* <code> mail.jar</code>
* <code> activation.jar </code>
           
<h4>Before Running/Building with Maven : <h4>

Enter Manually :
 
* Path for Database-Credentials.txt File in <code> DatabaseConnection.java </code>.
* Path for EmailCredentials.txt File in <code> Mainclass.java </code>.
* Base Links to be Crawled in <code> Mainclass.java </code>.

<h2>How to Build it</h2>

In order to build and use Phoneutria, download the source code by cloning it via your Git client:
<p>
<code> https://github.com/milouk/Web_Crawler.git </code>
</p>

Phoneutria uses [Maven](https://maven.apache.org/download.cgi) to manage the project, therefore your need to compile the Java source file and generate the Phoneutria-jar-with-dependencies.jar archive by using the following goal : 
<p>
<code> assembly:single </code>
</p>

To execute the Phoneutria-jar-with-dependencies.jar File, change Directory to Phoneutria-jar-with-dependencies.jar Directory and type the following Command Prompt(CMD) command :
<p>
<code> Java -jar Phoneutria-jar-with-dependencies.jar </code>
</p>

<h2>Extra Info</h2> 

For more information about how Phoneutria works, refer to our JavaDoc section located at :
<code> https://github.com/milouk/Web_Crawler/tree/master/doc </code>

<h2>License<h2>

Copyright (c) 2016, Athens University of Economics and Business.
