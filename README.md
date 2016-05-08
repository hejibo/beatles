# Web Crawler on Raspberry pi 3
  1. Crawling about 47,000 articles on www.csdn.net
  2. Statistics Frequency

# Usage
  1. make
  2. cd build
  3. screen java -jar -Xmx700m beatles-1.0.1-SNAPSHOT.jar getweb
  4. screen java -jar -Xmx700m beatles-1.0.1-SNAPSHOT.jar word 1 47000

  1. getweb -- Crawling about 47,000 articles on www.csdn.net
  2. word 1 47000 -- Statistics Frequency between article id 1 to article id 47000
  3. goto raspberry pi'ipaddress:8082 JDBC URL:jdbc:h2:./beatles-h2 username:sa password:sa
