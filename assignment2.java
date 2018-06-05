package zuoye2;

import java.io.*;
import weka.core.Instance;

import java.util.*;
import java.util.regex.Pattern;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class assignment2 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

       // make emote
		Pattern happyEmotes = Pattern.compile(".*(:\\)|;\\)|\\(:|\\(;|♥|♡|☺).*");
		Pattern sadEmotes = Pattern.compile(".*(:\\(|;\\(|\\):|\\);|\\>:\\||\\|:\\<|:@).*");

		//reader stop worder
		String fileName = "StopWords.txt";
		String line = null;
		String stopwordlist="";
		try {
			FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/csi4107assignment1/src/csi4107assignment1/"+fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
                stopwordlist+=line;
                stopwordlist+=" ";
			}
			 bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '"+fileName+"'");
			}
		catch(IOException ex) {
        System.out.println(
            "Error reading file '" + fileName + "'");  }
        
        String[] stopwords = stopwordlist.split(" ");
        
//read negative.txt
        Set<String> negativeset = new HashSet<String>();
        String fileName1 = "negative.txt";
		String line1 = null;
		String negativelist="";
		try {
			FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/zuoye2/src/zuoye2/"+fileName1);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line1 = bufferedReader.readLine()) != null) {
				negativelist = line1.toLowerCase();
                negativeset.add(negativelist);
			}
			 bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '"+fileName1+"'");
			}
		catch(IOException ex) {
        System.out.println(
            "Error reading file '" + fileName1 + "'");  }
        

 //read positive.txt
        Set<String> positiveset = new HashSet<String>();
        String fileName2 = "positive.txt";
		String line2 = null;
		String positivelist="";
		try {
			FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/zuoye2/src/zuoye2/"+fileName1);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line2 = bufferedReader.readLine()) != null) {
				positivelist = line2.toLowerCase();
				positiveset.add(positivelist);
			}
			 bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '"+fileName2+"'");
			}
		catch(IOException ex) {
        System.out.println(
            "Error reading file '" + fileName2 + "'");  }
//read twitter and build bog of word
		HashMap<String, Boolean> exm = new HashMap<String, Boolean>();

		HashMap<String, Boolean> qum = new HashMap<String, Boolean>();
		HashMap<String, Boolean> poe = new HashMap<String, Boolean>();
		HashMap<String, Boolean> noe = new HashMap<String, Boolean>();
		HashMap<String, Integer> po = new HashMap<String, Integer>();
		HashMap<String, Integer> ne = new HashMap<String, Integer>();
		Map<String, Map<String, Integer>> bagofword = new HashMap<String, Map<String, Integer>>();
      		String fileName3 = "semeval_twitter_data.txt";
      		line1 = null;
      		String contentlist="";
      		try {
      			FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/zuoye2/src/zuoye2/"+fileName3);
      			BufferedReader bufferedReader = new BufferedReader(fileReader);
      			while((line1 = bufferedReader.readLine()) != null) {
      		    int negative = 0;
    				int positive = 0;
    				HashMap<String, Integer> wordshash = new HashMap<String, Integer>();
    				contentlist = line1.toString();
    				String[] contents = contentlist.split("	");
    			
    				String[] words = contents[3].split(" ");
 //remove stop word
    				for(int i=0;i<words.length;i++) {
    					for(int j=0;j<stopwords.length;j++) {
    						words[i]=words[i].replaceAll("\\s","");
    						if(words[i].equals(stopwords[j])){
    							words[i]="";
    							j=stopwords.length;
    						}
    					}
    				}
    				String remove="";
    				for(int i=0;i<words.length;i++) {
    					if(words[i]=="") {
    						
    					}else {
    						remove+=words[i];
    						remove+=" ";
    					}
    				}

    				exm.put(words[0], remove.contains("!"));
    				qum.put(words[0], remove.contains("?"));
    				poe.put(words[0], happyEmotes.matcher(remove).matches());
    				noe.put(words[0], sadEmotes.matcher(remove).matches());
    				words=remove.split(" ");
    				for(int i=0;i<words.length;i++) {
    					String word = words[i].replaceAll("[^a-zA-z]", "");
    					word = word.toLowerCase();
    					Integer count = wordshash.get(word);
    					if (count == null) {
							wordshash.put(word, 1);
						} else {
							count++;
							wordshash.put(word, count);
						}
    					if (positiveset.contains(word)) {
							positive += 1;
						} 
							
							if (negativeset.contains(word)) {
								negative += 1;
							} 
								po.put(contents[0]+contents[1], positive);
								ne.put(contents[0]+contents[1], negative);
								bagofword.put(contents[0]+contents[1], wordshash);
						}
    				
    				if (negative == 0) {
    					positive = positive * 2 + 1;
    				}
    				}
      			bufferedReader.close();
    				}
    				catch(FileNotFoundException ex) {
    					System.out.println("Unable to open file '"+fileName3+"'");
    					}
    				catch(IOException ex) {
    		        System.out.println(
    		            "Error reading file '" + fileName3 + "'"); 
    				}
      		
    //pointer =null;
    	//pointer = twittermessage.head.next;

    //while(pointer != null) {
    //for(int i =0;i<pointer.single_word.length;i++) {
    //if(pointer.single_word[i]==null) {
    //System.out.print("");
    //}else {
    	//System.out.print(pointer.single_word[i]);
    	//}
    		
    //}
    //pointer= pointer.next;
    //}
      		
      		Vector<String> vocabVector = new Vector<String>();

    		        FastVector<Attribute> att;
    				FastVector<String> attv;
    				FastVector<String> exclamationm;
    				FastVector<String> questionm;
    				FastVector<String> posstiveem;
    				FastVector<String> negativeem;
    				Instances instance;
    				att = new FastVector<Attribute>();
    				attv = new FastVector<String>();
    				attv.addElement("positive");
    				attv.addElement("negative");
    				attv.addElement("neutral");
    				attv.addElement("objective");
    				att.addElement(new Attribute("Op", attv));
    				exclamationm = new FastVector<String>();
    				exclamationm.addElement("Y");
    				exclamationm.addElement("N");
    				att.addElement(new Attribute("Ex", exclamationm));
    				questionm = new FastVector<String>();
    				questionm.addElement("Y");
    				questionm.addElement("N");
    				att.addElement(new Attribute("Qu", questionm));
    				posstiveem = new FastVector<String>();
    				posstiveem.addElement("Y");
    				posstiveem.addElement("N");
    				att.addElement(new Attribute("PoE", posstiveem));
    				negativeem = new FastVector<String>();
    				negativeem.addElement("Y");
    				negativeem.addElement("N");
    				att.addElement(new Attribute("NeE", negativeem));
    				att.addElement(new Attribute("Pos"));
    				att.addElement(new Attribute("Neg"));
    				for (String word : vocabVector) {
    					att.addElement(new Attribute(word));
    				}
    				instance = new Instances("state", att, 0);
    			
    				try{
    					for (String tID : bagofword.keySet()) {			
    					Map<String, Integer> content = bagofword.get(tID);
    					
    					List<Double> values = Arrays.asList(
    						
    							(double) exclamationm.indexOf(exm.get(tID) ? "Y" : "N"), 
    							(double) questionm.indexOf(qum.get(tID) ? "Y" : "N"), 
    							(double) posstiveem.indexOf(poe.get(tID) ? "Y" : "N"),
    							(double) negativeem.indexOf(noe.get(tID) ? "Y" : "N"),
    							(double) po.get(tID),
    							(double) ne.get(tID)
    					);
    				Stream<String> temp = vocabVector.stream();
    			//Stream<Object> tempob= temp.map(key -> (double) (content.containsKey(key) ? content.get(key) : 0));
    					//temp.map(mapper)
    					//List<Object> vo= tempob.collect(Collectors.toList());
    					//List<Double> vocab = tempob.collect(Collectors.toList());
    					List<Double> vocab = temp.map(key -> (double) (content.containsKey(key) ? content.get(key) : 0)).collect(Collectors.toList());
    					List<Double> union = Stream.concat(values.stream(), vocab.stream()).collect(Collectors.toList());
    					double[] myarry = union.stream().mapToDouble(d -> d).toArray();//Double to double
    					instance.add(new DenseInstance(1.0,myarry));//fill data
    				}
		}
        		catch(NullPointerException np){
        					throw np;
        				}
    				
    				
    				ArffSaver result= new ArffSaver();
    				result.setInstances(instance);
    				result.setFile(new File("outpur.arff"));
    				result.writeBatch();
    				System.out.println("output file has been printed");

    		    }}
      		
      		
      		