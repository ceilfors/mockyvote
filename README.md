mockyvote
=========
This is a shameless demo web application repository for my failed job application as mySociety web developer position.

Running this app
----------------
1. Pre requisite: JDK 1.8 (The REST services I'm using is still up which is YourNextMP and MapIt)
2. Set JDK to JAVA_HOME
3. Run ./gradlew run
4. Launch localhost:8080 at Chrome (the only browser I tested)

Technology stack
----------------
- AngularJS
- Spring Boot + Spring Data JPA + Spring Web
- Groovy

The task
--------
We would like you to build a demo web application that could be used alongside a live-streamed election broadcast, to provide an unscientific straw-poll of the voting habits of the viewers. (Please note that for various reasons we wouldn’t launch a site like this; this is just an exercise.)

The demo should allow users to specify their parliamentary (Westminster) constituency and enter answers to the questions “Are you going to vote?” and “Who are you going to vote for?”. It should display the results of these questions nationally and for each constituency.

You may use any freely available programming languages, modules and web services. We will want to try out your code on our own machines so if there are any unusual pre-requisites please document them.

You must not spend more than four hours on the task. We cannot, of course, tell if you decide to cheat, but if you do so you should be aware that mySociety’s strong ethos of integrity is not going to make it an ideal place for you to work.

We know four hours is not a long time, so please consider what you can realistically do in the time, and when you submit your work for review, please give us an idea of what you would do next if you had more time. We expect you to send us something that works, even if you have just put in stub code or dummy data.

Please treat this as a serious piece of code and apply best practices. Lastly, don’t forget that this code is being written for others to scrutinise - if you are selected for interview you will be asked about it.

Important note: Please do not put anything that personally identifies you in the code. This is so that we can examine what you produce without any possibility of bias - we’ll separate your work from your name and email address after you submit your response.

The feedback from mySociety reviewers
-------------------------------------
Positives:
+ It works!
+ Nice UI/UX, simple question per screen
+ Used YourNextMP and MapIt
+ Postcode constituency selection
+ Gives you the list of candidates from your constituency
+ Good use of Angular, Bootstrap and Bower
+ Some tests, tests use mocking
+ Uses logging
+ Some discussion of future work

Negatives:
- Technology choice: Java/Groovy for a Ruby/Python job doesn't demonstrate
relevant experience well
- Angular for a project this size is perhaps overkill; also means it
doesn't work without JavaScript, but the JS looks very good
- Went over time limit, though admits to it
- Pet peeve: Can’t hit enter on the postcode form
- Constituency name is fetched with AJAX and the page sits there looking
broken until it loads; why not do it on the POST from the postcode form?
- Candidates exist, which is good, but parties don’t seem to
- Test fails - NullPointerException on Line 61 of YNMP service test
- Very short README, no explanation of where the code is or how it’s
structured

The reviewers noted that this was one of the stronger submissions; however,
the candidates who secured interviews had demonstrated more skill and
familiarity with the languages specified in the job advert.
