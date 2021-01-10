import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
 public String url;
 public String name;
 public WordCounter counter;
 public double score;
 
 public WebPage(String url,String name,double score){
  this.url = url;
  this.name = name;
  this.score=score;
  this.counter = new WordCounter(url); 
 }
 




 public double setScore(ArrayList<Keyword> keywords) throws IOException{
  if(this.name==null) {
   return 0;
  }
  score = 0;
  for(Keyword k : keywords){  
   score += k.weight*counter.countKeyword(k.name); 
  }
  return score;
 }

}