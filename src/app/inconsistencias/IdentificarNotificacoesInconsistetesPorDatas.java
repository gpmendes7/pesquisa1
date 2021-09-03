package app.inconsistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class IdentificarNotificacoesInconsistetesPorDatas {
	
	private static FileWriter fileWriter1;
	private static FileWriter fileWriter2;
	private static FileWriter fileWriter3;
	private static FileWriter fileWriter4;
	private static FileWriter fileWriter5;
	private static FileWriter fileWriter6;
	private static FileWriter fileWriter7;
	private static FileWriter fileWriter8;
	private static FileWriter fileWriter9;
	
	public static void main(String[] args) throws IOException {
		fileWriter1 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataNotificacaoEDataEncerramento.txt");
		fileWriter2 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataNotificacaoEDataInicioSintomas.txt");
		fileWriter3 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataNotificacaoEDataTeste.txt");
		fileWriter4 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataNotificacaoEDataInternacao.txt");
		fileWriter5 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataEncerramentoEDataInicioSintomas.txt");
		fileWriter6 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataEncerramentoEDataTeste.txt");
		fileWriter7 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataEncerramentoEDataInternacao.txt");
		fileWriter8 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataInicioSintomasEDataTeste.txt");
		fileWriter9 = new FileWriter("./arquivos/txt/inconsistencias/individuais/inconsistenciaEntreDataInicioSintomasEDataInternacao.txt");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select n from Notificacao n";
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);
		
		List<Notificacao> notificacoes = query.getResultList();
		
		for (Notificacao notificacao : notificacoes) {			
			  Date dataNotificacao = notificacao.getDataNotificacao(); 
			  Date dataEncerramento = notificacao.getDataEncerramento();
			  Date dataInicioSintomas = notificacao.getDataInicioSintomas();
			  Date dataTeste = notificacao.getDataTeste();
			  Date dataInternacao = notificacao.getDataInternacao();
			 
			  inconsistenciaEntreDataNotificacaoEDataEncerramento(notificacao, dataNotificacao, dataEncerramento);
			  inconsistenciaEntreDataNotificacaoEDataInicioSintomas(notificacao, dataNotificacao, dataInicioSintomas);
			  inconsistenciaEntreDataNotificacaoEDataTeste(notificacao, dataNotificacao, dataTeste);
			  inconsistenciaEntreDataNotificacaoEDataInternacao(notificacao, dataNotificacao, dataInternacao);
			  inconsistenciaEntreDataEncerramentoEDataInicioSintomas(notificacao, dataEncerramento, dataInicioSintomas);
			  inconsistenciaEntreDataEncerramentoEDataTeste(notificacao, dataEncerramento, dataTeste);
			  inconsistenciaEntreDataEncerramentoEDataInternacao(notificacao, dataEncerramento, dataInternacao);
			  inconsistenciaEntreDataInicioSintomasEDataTeste(notificacao, dataInicioSintomas, dataTeste); 
			  inconsistenciaEntreDataInicioSintomasEDataInternacao(notificacao, dataInicioSintomas, dataInternacao);
		}
		
		em.close();
		emf.close();
		
		fileWriter1.close();
		fileWriter2.close();
		fileWriter3.close();
		fileWriter4.close();
		fileWriter5.close();
		fileWriter6.close();
		fileWriter7.close();
		fileWriter8.close();
		fileWriter9.close();
	}

	private static void inconsistenciaEntreDataNotificacaoEDataEncerramento(Notificacao notificacao, Date dataNotificacao, Date dataEncerramento) throws IOException {
		if(dataNotificacao != null && dataEncerramento != null
			&& dataEncerramento.before(dataNotificacao)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de notificação posterior a data de encerramento");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter1.write("***************************\n");
			  fileWriter1.write("Notificação com data de notificação posterior a data de encerramento\n");
			  fileWriter1.write(notificacao + "\n");
			  fileWriter1.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataNotificacaoEDataInicioSintomas(Notificacao notificacao, Date dataNotificacao, Date dataInicioSintomas) throws IOException {
		if(dataNotificacao != null && dataInicioSintomas != null
			&& dataNotificacao.before(dataInicioSintomas)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de início sintomas posterior a data de notificação");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter2.write("***************************\n");
			  fileWriter2.write("Notificação com data de início sintomas posterior a data de notificação\n");
			  fileWriter2.write(notificacao + "\n");
			  fileWriter2.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataNotificacaoEDataTeste(Notificacao notificacao, Date dataNotificacao, Date dataTeste) throws IOException {
		if(dataNotificacao != null && dataTeste != null
			&& dataTeste.before(dataNotificacao)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de notificação posterior a data de teste");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter3.write("***************************\n");
			  fileWriter3.write("Notificação com data de notificação posterior a data de teste\n");
			  fileWriter3.write(notificacao + "\n");
			  fileWriter3.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataNotificacaoEDataInternacao(Notificacao notificacao, Date dataNotificacao, Date dataInternacao) throws IOException {
		if(dataNotificacao != null && dataInternacao != null
			&& dataInternacao.before(dataNotificacao)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de notificação posterior a data de internação");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			
			  fileWriter4.write("***************************\n");
			  fileWriter4.write("Notificação com data de notificação posterior a data de internação\n");
			  fileWriter4.write(notificacao + "\n");
			  fileWriter4.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataEncerramentoEDataInicioSintomas(Notificacao notificacao, Date dataEncerramento, Date dataInicioSintomas) throws IOException {
		if(dataEncerramento != null && dataInicioSintomas != null
			&& dataEncerramento.before(dataInicioSintomas)) {
			 /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de encerramento posterior a data de início sintomas");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			
			  fileWriter5.write("***************************\n");
			  fileWriter5.write("Notificação com data de encerramento posterior a data de início sintomas\n");
			  fileWriter5.write(notificacao + "\n");
			  fileWriter5.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataEncerramentoEDataTeste(Notificacao notificacao, Date dataEncerramento, Date dataTeste) throws IOException {
		if(dataEncerramento != null && dataTeste != null
			&& dataEncerramento.before(dataTeste)) {
			 /*  
			  System.out.println("***************************");
			  System.out.println("Notificação com data de teste posterior a data de encerramento");
			  System.out.println(notificacao);
			  System.out.println("***************************");
			 */
			  
			  fileWriter6.write("***************************\n");
			  fileWriter6.write("Notificação com data de teste posterior a data de encerramento\n");
			  fileWriter6.write(notificacao + "\n");
			  fileWriter6.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataEncerramentoEDataInternacao(Notificacao notificacao, Date dataEncerramento, Date dataInternacao) throws IOException {
		if(dataEncerramento != null && dataInternacao != null
			&& dataEncerramento.before(dataInternacao)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de internação posterior a data de encerramento");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter7.write("***************************\n");
			  fileWriter7.write("Notificação com data de internação posterior a data de encerramento\n");
			  fileWriter7.write(notificacao + "\n");
			  fileWriter7.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataInicioSintomasEDataTeste(Notificacao notificacao, Date dataInicioSintomas, Date dataTeste) throws IOException {
		if(dataInicioSintomas != null && dataTeste != null
			&& dataTeste.before(dataInicioSintomas)) {
			  /*  
				System.out.println("***************************");
				System.out.println("Notificação com data de início sintomas posterior a data de teste");
				System.out.println(notificacao);
				System.out.println("***************************");
			  */
			  
			  fileWriter8.write("***************************\n");
			  fileWriter8.write("Notificação com data de início sintomas posterior a data de teste\n");
			  fileWriter8.write(notificacao + "\n");
			  fileWriter8.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEntreDataInicioSintomasEDataInternacao(Notificacao notificacao, Date dataInicioSintomas, Date dataInternacao) throws IOException {
		if(dataInicioSintomas != null && dataInternacao != null
			&& dataInternacao.before(dataInicioSintomas)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificação com data de início sintomas posterior a data de internação");
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter9.write("***************************\n");
			  fileWriter9.write("Notificação com data de início sintomas posterior a data de internação\n");
			  fileWriter9.write(notificacao + "\n");
			  fileWriter9.write("***************************\n");
		  }
	}

}
