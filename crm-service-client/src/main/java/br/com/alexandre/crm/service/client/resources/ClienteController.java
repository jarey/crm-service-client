package br.com.alexandre.crm.service.client.resources;

import org.apache.log4j.Logger;

import br.com.alexandre.crm.service.entity.Cliente;
import br.com.alexandre.crm.service.entity.Erro;
import br.com.alexandre.crm.service.repository.ClienteRepository;
import br.com.alexandre.crm.service.repository.ClienteRepositoryException;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
@Path(value="/cliente")
public class ClienteController {
	private final ClienteRepository clienteRepository;		
	private final Result result;
	
	private Logger logger = Logger.getLogger(getClass()); 
	
	public ClienteController(final ClienteRepository clienteRepository, final Result result) {
		this.clienteRepository = clienteRepository;
		this.result = result;
	}
	
	@Get
	@Path("/{id}")
	public void cliente(final String id) {
		try {
			final Cliente cliente = clienteRepository.buscarPeloId(id);		
			result.use(Results.json()).from(cliente).recursive().serialize();
			logger.info("Serializando objeto cliente");
		} catch (ClienteRepositoryException e) {
			logger.error("Ocorreu um erro ao efetuar a busca dos dados do cliente", e);
						
			final Erro erro = new Erro(e.getCode(), e.getMessage());
			logger.info("Serializando objeto erro: " + erro);			
			result.use(Results.json()).from(erro).recursive().serialize();			
		}
	}	
}
