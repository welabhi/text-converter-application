
Note:-
After looking into the requirement document, it has been suggested to use Map. But I have used a List instead of a Map because after giving a thought 
it seems like there’s no need for key-based lookup, and a List can better preserves order, and we can avoid overhead of hashCode() and equals() method.


This application parses raw text input, splits it into sentences and words, sorts the words within each sentence, and outputs the result in either XML format.

The solution is implemented using Java 17 and Spring Boot, and is designed to handle input text efficiently.

Functions:
1) parse raw text from standard input.
2) split text into sentences and words.
3) sort words alphabetically in sentence object.
4) The final output is in XML format.
5) Unit test written using Junit and AssertJ

How to run:
1) Unzip and Import the project in IntelliJ.
2) Go to src → test → service → TextParserServiceTest.java
3) Execute individual test 
4) Output can be seen on Target folder with name "output.xml".
