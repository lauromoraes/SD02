package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import resource.Resource;

public class Task implements ITask {

	protected String dataI;
	protected String dataO;
	protected Set<String> words;
	protected Resource<String> tasks_to_do;
	protected boolean is_working;
	protected HashMap<String, HashMap<Integer, LinkedList<String>>> map;

	public Task() {
		// TODO Auto-generated constructor stub
		this.words = new HashSet<String>();
		this.is_working = true;
	}

	@Override
	public void execute(List<Object> param) {
		// TODO Auto-generated method stub
		while(is_working) {
			String s = this.tasks_to_do.get();
			this.split_words(s);
			
		}
		
		
		String msg = this.info();
		System.out.println(msg);
	}

	public String info() {

		String	info_class		= getClass().toString();
		String	info_object		= this.toString();
		long	info_thread_id	= Thread.currentThread().getId();
		long 	info_timestamp	= System.nanoTime();

		String msg = "";
		msg += "{\n";
		msg += "\tClass : {"		+info_class		+"};\n";
		msg += "\tObject : {"		+info_object	+"};\n";
		msg += "\tThreadI : {"		+info_thread_id	+"};\n";
		msg += "\nTimestamp : {"	+info_timestamp	+"};\n";
		msg += "}\n";

		return msg;
	}

	public void set_input(String I) {
		this.dataI = I;
	} 

	public String get_result() {
		return this.dataO;
	}

	@Override
	public void run() {
		// Metodo a ser executado pela Thread
		this.execute(null);
	}
	
	public void split_words(String text) {
		this.words.clear();
		String[] w = dataI.split("[\\p{Punct}\\s]+");
		for(String s : w) {this.words.add(s);}
	}
	
	public void execute_Levenshtein() {
		for(String w : this.words) {
			
		}
	}

	public List<String> Levenshtein(String s, Set<String> textos, float threshold){
		List<String> aux = new LinkedList<String>();
		float size2 = s.length();

		for(String umTexto:textos){
			float size1 = umTexto.length();    		
			float porcentagem =0;

			if(size1>size2) porcentagem = size1/size2;
			else porcentagem = size2/size1;

			//palavras com menos do que 50% de diferenca nos tamanhos
			if(porcentagem<1.5){
				float r = StringUtils.getLevenshteinDistance(s, umTexto);
				//X% de diferença ou menos 
				if(((r/size1)>=0)&&((r/size1)<threshold)) aux.add(umTexto);
			}	
		}
		return aux;
	}

	/**
	 * Metodo que dada uma palavra é possivel saber qual palavra é a mais similar. Neste método
	 * o grau de diferença é fixado em 20%
	 * @param s a palavra a ser analisada
	 * @param textos é o conjunto de palavras onde se alemja buscar a mais similar
	 * @return a palavra mais similar ou "" - VAZIO
	 */
	public String Levenshtein(String s, Set<String> textos){
		String aux = "";
		float diff=0;

		float size2 = s.length();

		for(String umTexto:textos){
			//System.err.println(s +"  &&  " + umTexto);
			float size1 = umTexto.length();    		
			float porcentagem =0;


			if(size1>size2) porcentagem = size1/size2;
			else porcentagem = size2/size1;

			//palavras com menos do que 50% de diferenca nos tamanhos
			if(porcentagem<1.5){
				float r = StringUtils.getLevenshteinDistance(s, umTexto);
				//X% de diferença ou menos 
				if(((r/size1)>=0)&&((r/size1)<=0.43)) 
					if(diff==0){
						diff = r/size1;
						aux=umTexto;
					}else
						if((r/size1)<diff){
							aux=umTexto;
							diff = r/size1;
						}
			}	
		}
		return aux;
	}
}

