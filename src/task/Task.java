package task;



import java.util.LinkedList;

public class Task<E> implements ITask<E> {

	protected Integer op_code;
	protected LinkedList<Object> params;
	protected E result;


	public Task(LinkedList<Object> data_input) {
		op_code = (Integer) data_input.pop();
		params = data_input;
	}
	
	public void split_words(String text) {
//		this.words.clear();								// Limpa o conjunto de palavras únicas
//		String[] w = dataI.split("[\\p{Punct}\\s]+");	// Quebra a string de entrada pelos separadores
//		for(String s : w) {this.words.add(s);}			// Adiciona as palavras encontradas em um conjunto de palavras unicas
//		this.threshold = Integer.parseInt( w[0] );		// Armazena o valor de threshold
	}

	@Override
	public Integer get_op_code() {
		return op_code;
	}

	@Override
	public LinkedList<Object> get_params() {
		return params;
	}

	@Override
	public E get_result() {
		return result;
	}

	@Override
	public void set_result(E result) {
		this.result = result;
	}

}

