# Card Game

### Introduction
The Crad Game is a java project which makes use of the multi threading. The game is initiated by
the user, choosing the amount of players and the crads whithin the playing pack. This Project will
how a computer can play a thread safe card game on its own.
### App Features
- Provides a playable card game 
- The user can chose the amount of players playing 
- The user can chose create his own pack 
- The system will check that there is a correct amount of crads relitive to player number  
- The system will not crash or start before the user puts in the correct inputs
- The system will play the game on its own untill there is a winner
- The system will log every action of each player
- The system will log the crads in each deck after the game ends
- Test folder containing each class test

### Prerequisites
  - Java
  - JUnit (Using version 4)
  - An IDE or texteditor (ex. Eclipse, Visual Studio Code, NetBeans, etc.)
  
### Installation
To enable **JUnit**:
Add Junit to the java project class path

### How to use
- Compile and run CardGame.java in termial (mac), command prompt (windows)
- The system will ask you to input the number of players participating in the game
- The system will then ask you to input a valid file with the values of the crads to be added to the pack
- **MAKE SURE** the pack file contains 8 * the number of players playing
- **MAKE SURE** the pack file exits within the src folder, **Should** be inputed in format src/
- The system will only start he game once every input has been validated
- After the game finishes the player and card deck log files will be created with every action of the game

### Developer's guide
- Download and place all the files in one direcotory
- Create your own pack file containing values for each card row by row, and place it in the src folder
- First, make sure that the pack contains the correct amount of values (8*the number of players)
- Open up a terminal and go to the src folder of the directory 
- Compile the CradGame.java file and then run it
- System will ask you to input the number of players playing and a pack file which you created, the game will not start if pack cannot be created
- There is also a folder containing all of our unit tests for each class which can be used to debug functions

### Testing 
- **MAKE SURE** JUnit 4 is in the java project class path
- Open the test folder within the project
- Individual test files for every class have been created and so has a test suite will every class
- Individual methods as entire class methods can be tested
- Open the file within an IDE, we used VS code
- If you want to test every method in the project open the TestSuite.java file 
- If you wannt to test certain methods oepn the Test folder with the name of the class you would like to test
- In VS code if you want to run a method, on the left side there will be a button you can press which will run the test for you
- **Recommend** using IDE, easier to undesratnd and run

### Links and Sources
- JUnit: https://junit.org/junit4/faq.html#started_2

### License
Copyright (c) [2020] [Grygoriy Vanetsyan] [Matthew Trenchard]
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files "Card Game", to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


   
