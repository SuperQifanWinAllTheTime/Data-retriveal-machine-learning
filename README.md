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

#weka result as following:<br />
=== Stratified cross-validation ===
=== Summary ===

Correctly Classified Instances        3501               48.4232 %
Incorrectly Classified Instances      3729               51.5768 %
Kappa statistic                          0.2955
Mean absolute error                      0.2747
Root mean squared error                  0.4356
Relative absolute error                 79.325  %
Root relative squared error            104.691  %
Total Number of Instances             7230     

=== Detailed Accuracy By Class ===

                 TP Rate  FP Rate  Precision  Recall   F-Measure  MCC      ROC Area  PRC Area  Class
                 0.517    0.158    0.732      0.517    0.606      0.383    0.764     0.745     positive
                 0.572    0.193    0.392      0.572    0.465      0.330    0.769     0.445     negative
                 0.279    0.164    0.316      0.279    0.296      0.120    0.611     0.289     neutral
                 0.572    0.168    0.381      0.572    0.458      0.346    0.789     0.418     objective
Weighted Avg.    0.484    0.167    0.528      0.484    0.492      0.312    0.736     0.544     

=== Confusion Matrix ===

    a    b    c    d   <-- classified as
 1698  639  498  449 |    a = positive
  199  739  201  154 |    b = negative
  305  387  431  424 |    c = neutral
  119  120  234  633 |    d = objective

for more ducumentation about weka, links are below:
https://www.cs.waikato.ac.nz/ml/weka/documentation.html
