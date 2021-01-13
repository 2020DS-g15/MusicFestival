import java.util.ArrayList;

public class KeywordList {

 public ArrayList<Keyword> lst;

 public KeywordList(String[] keywords) {
  this.lst = new ArrayList<Keyword>();
  this.defaultKeyword();
  addKeyword(keywords);
 }

 public void defaultKeyword() {
  lst.add(new Keyword("音樂祭", 1));
  lst.add(new Keyword("音樂節", 1));
  
  lst.add(new Keyword("樂團", 1));
  lst.add(new Keyword("專場", 1));
  lst.add(new Keyword("獨立樂團", 1));
  lst.add(new Keyword("陣容", 1));
  
  lst.add(new Keyword("免費", 1));
  lst.add(new Keyword("票價", 1));
  lst.add(new Keyword("購票場次", 1));
  
  lst.add(new Keyword("時間", 1));
  lst.add(new Keyword("日期", 1));
  lst.add(new Keyword("地點", 1));
  
  lst.add(new Keyword("Simple Urban", 1));
  lst.add(new Keyword("簡單生活節", 1));
  lst.add(new Keyword("LUCfest", 1));
  lst.add(new Keyword("貴人散步", 1));
  lst.add(new Keyword("街角無常", 1));
  lst.add(new Keyword("大港", 1));
  lst.add(new Keyword("大港開唱", 1));
  
  lst.add(new Keyword("Legacy", 1));
  lst.add(new Keyword("小地方展演空間", 1));
  lst.add(new Keyword("The Wall", 1));
  lst.add(new Keyword("Zepp New Taipei", 1));
  lst.add(new Keyword("台北流行音樂中心", 1));
  lst.add(new Keyword("小巨蛋", 1));
  lst.add(new Keyword("樂悠悠之口", 1));
 }
 
 public void addKeyword(String[] keywords) {
  for (String keyword : keywords) {
   if(keyword.equals("")) {
    continue;
   } else {
    boolean found = false;
    for(Keyword k: lst) {
     if(k.name.equals(keyword)) {
      k.weight = 10;
      found = true;
      break;
     }
    }
    if(!found) {
     lst.add(new Keyword(keyword, 10));
    }
   }
  }
 }

 @Override
 public String toString() {
  String retVal = "Keywords:";
  for (Keyword keyword : lst) {
   retVal += " " + keyword.name;
  }
  return retVal;
 }

}