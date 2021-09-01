package app.copias;

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

public class IdentificarNotificaoesCopias {
	
	private static FileWriter fileWriter;
	
	public static void main(String[] args) throws IOException {
		fileWriter = new FileWriter("./redundancias/redundanciaVariosCampos.txt");
		
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
				  if(temRedundancia(notificacaoDuplicada, notificacao)) {
					  fileWriter.write("***************************\n");
					  fileWriter.write("Notificações com a mesma data de notificação, nome, classificação final, evolução caso, data de internação e data de encerramento\n");
					  fileWriter.write(notificacaoDuplicada + "\n");
					  fileWriter.write(notificacao + "\n");
					  fileWriter.write("***************************\n");
				  }
			  }
			  						  
			  notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento); 
		}
		 
		em.close();
		emf.close();
		
		fileWriter.close();
	}
	
	private static boolean temRedundancia(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		return temMesmaDataNotificacao(notificacaoDuplicada, notificacao) && temMesmaClassificacaoFinal(notificacaoDuplicada, notificacao)
			&& temMesmaEvolucaoCaso(notificacaoDuplicada, notificacao) && temMesmaDataEncerramento(notificacaoDuplicada, notificacao)
			&& temMesmaDataInternacao(notificacaoDuplicada, notificacao);
	}
	
	private static boolean temMesmaDataNotificacao(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataNotificacao1 = notificacaoDuplicada.getDataNotificacao();
		  Date dataNotificacao2 = notificacao.getDataNotificacao();
		  
		  if(dataNotificacao1 != null && dataNotificacao2 != null 
		  && dataNotificacao1.equals(dataNotificacao2)) {
			  return true;
		  } 
		  
		  return false;
	}
	
	private static boolean temMesmaClassificacaoFinal(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  String classificacaoFinal1 = notificacaoDuplicada.getClassificacaoFinal();
		  String classificacaoFinal2 = notificacao.getClassificacaoFinal();
		  
		  if(classificacaoFinal1 != null && classificacaoFinal2 != null 
		  && classificacaoFinal1.equals(classificacaoFinal2)) {
			  return true;
		  } 
		  
		  return false;
	}
	
	private static boolean temMesmaEvolucaoCaso(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  String evolucaoCaso1 = notificacaoDuplicada.getEvolucaoCaso();
		  String evolucaoCaso2 = notificacao.getEvolucaoCaso();
		  
		  if(evolucaoCaso1 != null && evolucaoCaso2 != null 
		  && evolucaoCaso1.equals(evolucaoCaso1)) {
			  return true;
		  } 
		  
		  return false;
	}

	private static boolean temMesmaDataEncerramento(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataEncerramento1 = notificacaoDuplicada.getDataEncerramento();
		  Date dataEncerramento2 = notificacao.getDataEncerramento();
		  
		  if(dataEncerramento1 != null && dataEncerramento2 != null 
		  && dataEncerramento1.equals(dataEncerramento2)) {
			  return true;
		  }
		  
		  return false;
	}
	
	private static boolean temMesmaDataInternacao(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataInternacao1 = notificacaoDuplicada.getDataInternacao();
		  Date dataInternacao2 = notificacao.getDataInternacao();
		  
		  if(dataInternacao1 != null && dataInternacao2 != null 
		  && dataInternacao1.equals(dataInternacao2)) {
			  return true;
		  }
		  
		  return false;
	}

}
