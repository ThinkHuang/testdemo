package cn.huang.test;

import java.util.ArrayList;

public class Demo {
	public static void main(String[] args) {
		
		ArrayList rptAllRecipientsList = new ArrayList();
		String[] rptRecipients = {"AA","BB"};
		String[] sDeftPrns = {"CC","DD"};
		if(rptRecipients != null){
			for(int i = 0; i < rptRecipients.length; i++){
				rptAllRecipientsList.add(rptRecipients[i]);
				System.out.println("rptRecipients " + i + rptRecipients[i]);
			}
			
		}
		if(sDeftPrns != null){
			for(int i = 0; i < sDeftPrns.length; i++){
				rptAllRecipientsList.add(sDeftPrns[i]);
				System.out.println("sDeftPrns " + i + sDeftPrns[i]);
			}
		}
		//String[] rptAllRecipients = (String[]) rptAllRecipientsList.toArray();
		for (int i = 0; i < rptAllRecipientsList.size(); i++) {
			String string = (String) rptAllRecipientsList.get(i);
			
			System.out.println(string);
		}
	}
}
