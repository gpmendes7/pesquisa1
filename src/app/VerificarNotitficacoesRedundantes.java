package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class VerificarNotitficacoesRedundantes {
	
	private static FileWriter fileWriter1;
	private static FileWriter fileWriter2;
	
	public static void main(String[] args) throws IOException {
		fileWriter1 = new FileWriter("./redundancias/redundanciaDataNotificacao.txt");
		fileWriter2 = new FileWriter("./redundancias/redundanciaDataEncerramento.txt");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql= "select n1" + 
				     " from Notificacao n1" +
				     " where n1.dataNascimento is not null"  + 
				     " and exists (" +
				     " select n2 from Notificacao n2" +
				     " where n2.paciente = n1.paciente" +
				     " and n2.numeroNotificacao != n1.numeroNotificacao )";
		
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);
		
		List<Notificacao> notificacoesDuplicadas = query.getResultList();
		
		while(!notificacoesDuplicadas.isEmpty()) { 
			  Notificacao notificacaoDuplicada = notificacoesDuplicadas.remove(0);
		  
			  List<Notificacao> notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento =
			  notificacoesDuplicadas.stream() 
			                        .filter(n -> n.getNomeCompleto().equals(notificacaoDuplicada.getNomeCompleto()) &&
			                        		n.getDataNascimento().equals(notificacaoDuplicada.getDataNascimento()))
			                        .collect(Collectors.toList());
			  						  
			  for (Notificacao notificacao : notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento) {
				  String evolucaoCaso1 = notificacaoDuplicada.getEvolucaoCaso();
				  String evolucaoCaso2 = notificacao.getEvolucaoCaso();
				  
				  if(evolucaoCaso1.equals(evolucaoCaso2)) {
					  redundanciaDataNotificacao(notificacaoDuplicada, notificacao);					  
					  redundanciaDataEncerramento(notificacaoDuplicada, notificacao);
				  }
			  }
			  						  
			  notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento); 
		}
		 
		em.close();
		emf.close();
		
		fileWriter1.close();
		fileWriter2.close();
	}
	
	private static void redundanciaDataNotificacao(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataNotificacao1 = notificacaoDuplicada.getDataNotificacao();
		  Date dataNotificacao2 = notificacao.getDataNotificacao();
		  
		  if(dataNotificacao1 != null && dataNotificacao2 != null 
		  && dataNotificacao1.equals(dataNotificacao2)) {
			  /* 
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso iguais e com mesma data de notificação");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter1.write("***************************\n");
			  fileWriter1.write("Notificações com evolução caso iguais e com mesma data de notificação\n");
			  fileWriter1.write(notificacaoDuplicada + "\n");
			  fileWriter1.write(notificacao + "\n");
			  fileWriter1.write("***************************\n");
		  }
	}

	private static void redundanciaDataEncerramento(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		Date dataEncerramento1 = notificacaoDuplicada.getDataEncerramento();
		  Date dataEncerramento2 = notificacao.getDataEncerramento();
		  
		  if(dataEncerramento1 != null && dataEncerramento2 != null 
		  && dataEncerramento1.equals(dataEncerramento2)) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso iguais e com mesma data de encerramento");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter2.write("***************************\n");
			  fileWriter2.write("Notificações com evolução caso iguais e com mesma data de encerramento\n");
			  fileWriter2.write(notificacaoDuplicada + "\n");
			  fileWriter2.write(notificacao + "\n");
			  fileWriter2.write("***************************\n");
		  }
	}

}
