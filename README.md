# Data-retriveal-machine-learning
This project goal is classify tweeter messages as expressing a positive opinion, a negative opinion, or no opinion(netural or object)<br /> 
## Task Description

The task has two sub-tasks: an expression-level task and a message-level task.  Participants may choose to participate in either or both tasks.
## Task A: Contextual Polarity Disambiguation
Given a message containing a marked instance of a word or a phrase, determine whether that instance is positive, negative or neutral in that context. The boundaries for the marked instance will be provided, i.e., this  is a classification task, NOT an entity recognition task.
## Task B: Message Polarity Classification
Given a message, decide whether the message is of positive, negative, or neutral sentiment. For messages conveying both a positive and negative sentiment, whichever is the stronger sentiment should be chosen.

In this project, the main focus is task B. 

Read more about the task at:
http://www.cs.york.ac.uk/semeval-2013/task2/

# Data
There are corpus of 12-20K messages on a range of topics.  Topics will include a mixture of entities (e.g., Gadafi, Steve Jobs), products (e.g., kindle, android phone), and events (e.g., Japan earthquake, NHL playoffs).  Keywords and twitter hashtags will be used to identify messages relevant to the selected topic.
The message corpus will then be divided as follows:
trial data: 2000 twitter messages -- released
training data: 8000-12,000 twitter messages (includes the trial dataset) -- released
development data: 2,000 twitter messages (can be used for training too) -- released
test data #1: 2000-4000 twitter messages -- released
test data #2: 2000-4000 SMS messages -- released

in this project, the data consists in approximately 8000 tweets.

The formate of the data is, for each line:
\<SID>\<tab>\<UID>\<tab>\<TOPIC>\<tab>\<positive|negative|neutral|objective>\<tab>\<TWITTER_MESSAGE><br /> 
Example of one line:<br /> 
100032373000896513 15486118 lady gaga "positive"<br /> 
Wow!! Lady Gaga is actually at the Britney Spears Femme Fatale Concert tonight!!! She still listens to her music!!!! WOW!!!

# Machine Learning(ML)
in this project, i will use machine learning(ML) algorithms from a tool named Weka. java is the main laugage. in this project, i used thorught its graphical user interface also directly from java programs through its API.

i wrote a program that extracts features from the tweets and save them in an .aff file. After that, i open arff file in Weka's GUI and run serval machine learning algorithms that are approprate for this task.
i stroe arff file as following format:
@RELATION example_rel<br />
@ATTRIBUTE a1 STRING<br />
@ATTRIBUTE a2 {Y,N}<br />
@ATTRIBUTE a3 NUMERIC @ATTRIBUTE a4 NUMERIC @ATTRIBUTE class {C1, C2, C3} <br />
@DATA<br />
Str1,Y ,1.4,0.2,C1 <br />
Str2,N,1.4,0.2,C2 <br />
Str3,Y ,1.3,0.2,C1 <br />
Str1,N,1.5,0.2,C1 <br />
Str4,Y ,1.4,0.2,C3<br />

#weka result as following:
for more ducumentation about weka, links are below:
https://www.cs.waikato.ac.nz/ml/weka/documentation.html
