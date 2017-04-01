<h1><b>Project "Phoneutria"</b></h1>

<h2><b>What is a Web Crawler?</b></h2>


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


<h2><b>How to Configure "Phoneutria"</b></h2>
<p>
Phoneutria is fully written in [Java (v. 8+)](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) and managed by [Maven (v. 3.3.9)](https://maven.apache.org/download.cgi).

<h4>Dependencies : </h4>

Download :
* <code> mail.jar</code>
* <code> activation.jar </code>
* <code> sqljdbc4.jar </code>

<p>
<i>Alterntatively Use the pom.xml file provided.</i>
           
<h4>Before Running/Building with Maven : <h4>

Enter Manually :
 * Path for Database-Credentials.txt File in <code> DatabaseConnection.java </code>.
 * Path for EmailCredentials.txt File in <code> Mainclass.java </code>.
 * Base Links to be Crawled in <code> Mainclass.java </code>.

<h2><b>How to Build it</b></h2>

In order to build and use Phoneutria, download the source code by cloning it via your Git client:
<p>
<code> https://github.com/milouk/Web_Crawler.git </code>

Phoneutria uses [Maven](https://maven.apache.org/download.cgi) to manage the project, therefore your need to compile the Java source file and generate the Phoneutria-jar-with-dependencies.jar archive by using the following goal : 
<p>
<code> assembly:single </code>

To execute the Phoneutria-jar-with-dependencies.jar File, change Directory to Phoneutria-jar-with-dependencies.jar Directory and type the following Command Prompt(CMD) command :
<p>
<code> Java -jar Phoneutria-jar-with-dependencies.jar </code>

<h2><b>Extra Info</b></h2> 

For more information about how Phoneutria works, refer to our JavaDoc section located at :
<p>
<code> https://github.com/milouk/Web_Crawler/tree/master/doc </code>

<h2><b>License</b></h2>

Copyright 2017 Athens University of Economics and Business

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<p>
