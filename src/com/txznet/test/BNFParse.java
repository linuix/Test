package com.txznet.test;



import java.util.*;

public class BNFParse{

    public Collection<String> parseCmd(String data) {
        if (isBNF(data)) {
            BNF bnf = new BNF(data);
//            Log.d("BNFParse","bnf:=" + bnf.toString());
            HashSet<String> cmds = new HashSet<String>();
            parseCmd(cmds,bnf,"");
            return cmds;
        }
        return null;
    }

    private void parseCmd(HashSet<String> set,BNF bnf,String str){
        if (bnf == null){
            set.add(str);
            return;
        }else {
            String[] cmds = bnf.bnf;
            int type = bnf.curType;
            for (int i = 0;i < cmds.length;i++){
                if (type == BNF.MANDATORY || type == BNF.REPEATABLE){
                    String cmd  = str + cmds[i];
                    parseCmd(set,bnf.next,cmd);
                }else if (type == BNF.OPTIONAL) {
                    parseCmd(set,bnf.next,str);
                    String cmd  = str + cmds[i];
                    parseCmd(set,bnf.next,cmd);
                }
            }
        }
    }

    public static boolean isBNF(String data){
        if (data.contains("[") && data.contains("]") && getCharCount(data,'[')==getCharCount(data,']')){
            return true;
        }
        if (data.contains("{") && data.contains("}") && getCharCount(data,'{') == getCharCount(data,'}')){
            return true;
        }
        if (data.contains("<") && data.contains(">") && getCharCount(data,'<') == getCharCount(data,'>')){
            return true;
        }
        if (data.contains("(") && data.contains(")") && getCharCount(data,'(') == getCharCount(data,')')){
            return true;
        }
        if (data.contains("（") && data.contains("）") && getCharCount(data,'（') == getCharCount(data,'）')){
            return true;
        }
        return false;
    }

    private static int getCharCount(String src,char c){
        int count = 0;
        for (int i = 0;i < src.length();i++){
            char ch = src.charAt(i);
            if (ch == c){
                count++;
            }
        }
        return count;
    }

    public static class BNF {

        private String[] bnf;
        /**
         * 必选
         */
        private static final int MANDATORY = 1;
        /**
         * 可选
         */
        private static final int OPTIONAL = 2;
        /**
         * 重复出现
         */
        private static final int REPEATABLE = 3;
        /**
         * 类型
         */
        private int curType;

        private BNF next;

        public BNF(String bnf){
            parse(bnf);
        }

        private void parse(String bnf){
//            Log.d("BNF","bnfData=" + bnf);
            if (bnf == null || bnf.equals("")){
                return;
            }
            char[] data = bnf.toCharArray();
            StringBuilder buffer = new StringBuilder();
            for (int i=0;i<data.length;i++){
                char c = data[i];
                String sub = null;
                switch (c){
                    case '[':
                        if (check(bnf,buffer,i)){
                            return;
                        }
                        break;
                    case ']':
                        curType = OPTIONAL;
                        setNext(buffer,bnf,i);
                        return;
                    case '{':
                        if (check(bnf,buffer,i)){
                            return;
                        }
                        break;
                    case '}':
                        curType = REPEATABLE;
                        setNext(buffer,bnf,i);
                        return;
                    case '（':
                    case '(':
                    case '<':
                        if (check(bnf,buffer,i)){
                            return;
                        }
                        break;
                    case '）':
                    case ')':
                    case '>':
                        curType = MANDATORY;
                        setNext(buffer,bnf,i);
                        return;
                    default:
                        buffer.append(c);
                        break;
                }
            }
            if (curType ==0){
                curType = MANDATORY;
            }
            setBnf(buffer);
        }

        private void setNext(StringBuilder buffer,String bnf,int index){
            setBnf(buffer);
            bnf = bnf.substring(index+1,bnf.length());
            if (bnf != null && !bnf.equals("")){
                setNext(new BNF(bnf));
            }
        }

        private boolean check(String bnf,StringBuilder buffer,int index){
            if (buffer.length() > 0){
                if (curType ==0){
                    curType = MANDATORY;
                }
                setBnf(buffer);
                if (index <= 0){
                    setNext(new BNF(bnf));
                }else {
                    setNext(new BNF(bnf.substring(0,index)));
                }
                return true;
            }
            return false;
        }

        private void setBnf(StringBuilder buffer){
            if (this.bnf == null) {
                String str = buffer.toString();
//                Log.d("BNF","bnf=" + str);
                StringBuilder sb = new StringBuilder();
                ArrayList<String> bnfList = new ArrayList<String>();
                for (int i =0;i < str.length(); i++) {
                    char c = str.charAt(i);
                    switch (c){
                        case '|':
                            String s = sb.toString();
                            s.replace(" ","");
                            s.replace("\r\n","");
                            bnfList.add(s);
                            sb.delete(0,sb.length());
                            break;
                        default:
                            sb.append(c);
                            break;
                    }
                }
                if (sb.length() > 0){
                    bnfList.add(sb.toString());
                    sb.delete(0,sb.length());
                }
                this.bnf = bnfList.toArray(new String[bnfList.size()]);
            }
        }

        public int getType(){
            return curType;
        }

        private void setNext(BNF bnf){
            this.next = bnf;
        }

        public BNF next(){
            return next;
        }

        @Override
        public String toString() {
            return "BNF{" +
                    "bnf=" + toBnfString() +
                    ", curType=" + curType +
                    ", next=" + next +
                    '}';
        }

        private String toBnfString(){
            if (bnf!=null){
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                for (int i = 0; i < bnf.length; i++) {
                    sb.append(bnf[i]);
                    if (i < bnf.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(']');
                return sb.toString();
            }
            return null;
        }

    }

}
