import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.Arrays;

/**
 * InfectStatistic
 * TODO
 *
 * @author ������
 * @version 1.0
 * 
 */
public class InfectStatistic {
	static int count=0;//�������ݵ�����
	
    public static void main(String[] args) throws IOException {   	
    	cmdArgs cmd=new cmdArgs(args);
    	int i=cmd.hasParam("-date");//�����������
    	if(i!=-1) {//��ָ������
    		readLog(args[i+1],true);
    	}  
    }
   
static class line{//ͳ��֮��Ĳ���ÿ���Ľṹ
	String location;//����λ��
	int grhz;//��Ⱦ��������
	int yshz;//���ƻ�������
	int recover;//��������
	int dead;//��������
	
	line(String plocation,int pgrhz,int pyshz,int precover,int pdead){
		location=plocation;
		grhz=pgrhz;
		yshz=pyshz;
		recover=precover;
		dead=pdead;
	}
	
	line(){		
	}

	/*
              * ���ܣ���ӡһ��ͳ��������Ϣ
              * �����������
              *����ֵ����Ϣ�ַ���
    */
	String printline() {
		return(location+" ��Ⱦ����"+grhz+"�� ���ƻ���"+yshz+"�� ����"+recover+"�� ����"+dead+"��");
	}
}

static class cmdArgs {//��ȡʹ������
    String[] args;
    
    cmdArgs(String[] passargs) {
        args=passargs;
    }
    
    /*
                     * ���ܣ��ж��Ƿ����ĳ����
                     * �����������Ҫ���������
                     *����ֵ�������������intֵ�����û�и������򷵻�-1
    */
    int hasParam(String key) {
        for(int i=0;i<args.length;i++) {
        	if(args[i].equals("key")) {
        		return i;
        	}
        }
        return -1;
    }   
}

     /*
              * ���ܣ��ж����ڵĺϷ���
              * ������������¸�����־��ʱ�䣬����֤�����ַ���
              *����ֵ��true,false
    */
    static boolean isCorrectdate(String lastdate,String date) {
    	if(isBefore(lastdate,date)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
     /*
     * ���ܣ��Ƚ����ڴ�С
     * ���������������Ҫ�Ƚϵ������ַ���
     *����ֵ��ǰ<�󷵻�true��ǰ>�󷵻�false
    */
    static boolean isBefore(String date1,String date2) {
    	String[] dsp1=date1.split("-");
    	String[] dsp2=date2.split("-");//�ָ�������
    	if(dsp1[0].compareTo(dsp2[0])>0) {//��ݹ���
    		return false;
    	}
    	else if(dsp1[0].equals(dsp2[0])){//������
    		if(dsp1[1].compareTo(dsp2[1])>0) {//�·ݹ���
    			return false;
    		}
    		else if(dsp1[1].equals(dsp2[1])) {//�·����
    			if(dsp1[2].compareTo(dsp2[2])>0) {//���ڹ���
    				return false;
    			}
    			else {
    				return true;
    			}
    		}
    		else {
    			return true;
    		}
    	}
    	else {
    		return true;
    	}   		
    }
    
    /*
              * ���ܣ���ȡ������־��ʱ��
              * �����������
              *����ֵ�������ַ���
    */
    static String getLastdate() {
    	String date="";
    	File file = new File("d:/log/");
        String[] filename = file.list();//��ȡ������־�ļ���      
    	date=filename[filename.length-1].substring(0,10);    	
    	return date;
    }
    
    /*
     * ���ܣ���ȡָ�������ļ���������־�е�����
     * ���������ָ�������ַ���
     *����ֵ������intֵ
    */
    static int findPot(String date) {
    	File file = new File("d:/log/");
        String[] filename = file.list();//��ȡ������־�ļ���      
    	for(int i=0;i<filename.length;i++) {
    		String datecut=filename[i].substring(0,10);//ֻ��ȡ�ļ���ǰ������
    		if(date.equals(datecut)) {
    			return i;
    		}
    	}
    	return -1;   	
    }
        
      /*
     * ���ܣ���ȡlog�ļ�
     * ���������ָ����������ڣ��Ƿ�ָ���������
     *����ֵ����
    */
    static void readLog(String date,boolean hasDate) throws IOException {
    	line[] all=new line[34];//��ʼ�����
	    for(int j=0;j<34;j++) {
	    	all[j]=new line();
	    }
    	if(hasDate==true) {
    		if(isCorrectdate(getLastdate(),date)) {//��������������ȷ��
    			int i=0;//������־��ȡ����
    			File file = new File("d:/log/");
    			String[] filename = file.list();//��ȡ������־�ļ���  			    
    			while(i<=findPot(date)) {   			
					FileInputStream fs=new FileInputStream("d:/log/"+filename[i]);
				    InputStreamReader is=new InputStreamReader(fs);
				    BufferedReader br=new BufferedReader(is);
				    String s="";				    
				    while((s=br.readLine())!=null){//һ��һ�ж�
					    String[] sp =s.split(" ");//�ָ������ַ���
					    statistics(sp,all);          	
		    	    }
				    br.close();
		    	}
    		}
    		else {//���ڲ���ȷ
    			System.out.print("��������ڳ�����Χ�����������");
    		}
    	}
    	else {//û����ָ������
    		int i=0;//������־��ȡ����
			File file = new File("d:/log/");
			String[] filename = file.list();//��ȡ������־�ļ���  
			while(i<filename.length) {   			
				FileInputStream fs=new FileInputStream("d:/log/"+filename[i]);
			    InputStreamReader is=new InputStreamReader(fs);
			    BufferedReader br=new BufferedReader(is);
			    String s="";				    
			    while((s=br.readLine())!=null){//һ��һ�ж�
				    String[] sp =s.split(" ");//�ָ������ַ���
				    statistics(sp,all);          	
	    	    }
			    br.close();
	    	}   		
    	}
    	printtxt(sortline(all));
    }
       
     /*
	     * ���ܣ�ͳ�Ƹ��ص����
	     * �����������־��ÿ�е��ַ������飬�ܵļ�¼����
	     *����ֵ����
    */
    static void statistics(String[] sp,line[] all) {   	
    	String location="";    	
    	location=sp[0];
    	line line1;
    	if(!isExistlocation(location,all)) {//�����ڶ�Ӧ��ʡ�ļ�¼
    		line1=new line(location,0,0,0,0);//�½�������   		
    		all[count]=line1;
    		count++;
    	}
    	else {
    		line1=getLine(location,all);//���ԭ�е�������
    	}    	
    	if(sp[1].equals("����")) {
    		if(sp[2].equals("��Ⱦ����")) {//��ø�Ⱦ����
    			line1.grhz+=Integer.valueOf(sp[3].substring(0,sp[3].length()-1));
    		}
    		else {//���ƻ���
    			line1.yshz+=Integer.valueOf(sp[3].substring(0,sp[3].length()-1));
    		}
    	}
    	else if(sp[1].equals("����")) {
    		line1.dead+=Integer.valueOf(sp[2].substring(0,sp[2].length()-1));
    	}
    	else if(sp[1].equals("����")) {
    		line1.recover+=Integer.valueOf(sp[2].substring(0,sp[2].length()-1));
    	}
    	else if(sp[1].equals("���ƻ���")) {
    		if(sp[2].equals("ȷ���Ⱦ")){
    			int change=Integer.valueOf(sp[3].substring(0,sp[2].length()-1));//�ı�����
    			line1.grhz+=change;
    			line1.yshz-=change; 			
    		}
    		else {//�������
    			String tolocation=sp[3];//����ʡ
    			int change=Integer.valueOf(sp[4].substring(0,sp[4].length()-1));//�ı�����
    			line line2;
    	    	if(!isExistlocation(tolocation,all)) {//�����ڶ�Ӧ��ʡ�ļ�¼
    	    		line2=new line(tolocation,0,0,0,0);//�½�������
    	    		all[count]=line2;
    	    		count++;
    	    	}
    	    	else {
    	    		line2=getLine(tolocation,all);//���ԭ�е�������
    	    	}
    			line1.yshz-=change;
    			line2.yshz+=change;
    		}
    	}
    	else if(sp[1].equals("�ų�")) {
    		line1.yshz-=Integer.valueOf(sp[3].substring(0,sp[3].length()-1));   		
    	}
    	else {//��Ⱦ�����������
    		String tolocation=sp[3];//����ʡ
			int change=Integer.valueOf(sp[4].substring(0,sp[4].length()-1));//�ı�����
			line line2;
	    	if(!isExistlocation(tolocation,all)) {//�����ڶ�Ӧ��ʡ�ļ�¼
	    		line2=new line(tolocation,0,0,0,0);//�½�������
	    		all[count]=line2;
	    		count++;
	    	}
	    	else {
	    		line2=getLine(tolocation,all);//���ԭ�е�������
	    	}
			line1.grhz-=change;
			line2.grhz+=change;   		
    	}
    }
    
     /*
              * ���ܣ��ҳ�ָ����ַ�Ƿ��Ѿ����ڼ�¼
              * ���������ʡ�����֣��ܵļ�¼����
              *����ֵ��true,false
    */
    static boolean isExistlocation(String location,line[] all) {
    	for(int i=0;i<count;i++) {
    		if(location.equals(all[i].location)) {
    			return true;
    		}
    	}
    	return false;    	
    }
    
     /*
              * ���ܣ��ҳ�ָ����ַ�ļ�¼
              * ���������ʡ�����֣��ܵļ�¼����
              *����ֵ��һ����¼
    */  
    static line getLine(String location,line[] all) {
    	for(int i=0;i<count;i++) {
    		if(location.equals(all[i].location)) {
    			return all[i];
    		}
    	}
    	return null;//�����õ�
    }
    
     /*
     * ���ܣ������м�¼�����txt�ļ�
     * ����������ܵļ�¼����all
     *����ֵ����
    */
    static void printtxt(line[] all) throws IOException {
    	File f = new File("d:\\output.txt");
        BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
        for(int i=0;i<count;i++) {//д��ͳ������
        	output.write(all[i].printline() + "\n");
        }
    	output.close();
    }
    
     /*
     * ���ܣ�ƴ��˳������all����
     * ����������ܵļ�¼����all
     *����ֵ������õ�all����
    */
    static line[] sortline(line[] all) {
    	String[] location=new String[count];
    	line[] result=new line[34];//�������
	    for(int j=0;j<34;j++) {
	    	result[j]=new line();
	    }
    	for(int i=0;i<count;i++) {
    		location[i]=all[i].location;
    	}    	
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(location, cmp);
        int j=0;//����ʡ��ƴ��˳������
        while(j<count) {
        	int i=0;
        	while(i<count) {
	        	if(all[i].location.equals(location[j])) {
	        		result[j]=all[i];
	        		j++;
	        	}
	        	i++;
        	}
        }
        return result;
    }    
}



