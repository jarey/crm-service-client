package br.com.alexandre.crm.service.repository;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alexandre.crm.service.client.AddressType;
import br.com.alexandre.crm.service.client.CustomerService;
import br.com.alexandre.crm.service.client.CustomerServiceException;
import br.com.alexandre.crm.service.client.CustomerServiceRequestType;
import br.com.alexandre.crm.service.client.CustomerServiceResponseType;
import br.com.alexandre.crm.service.client.ObjectFactory;
import br.com.alexandre.crm.service.entity.Cliente;
import br.com.alexandre.crm.service.entity.Endereco;

@Component
public class ClienteRepository {
	private final CustomerService customerService;

	@Autowired
	public ClienteRepository(final CustomerService crmService) {
		this.customerService = crmService;
	}
	
	public Cliente buscarPeloId(final String customerId) {
		try {
			return tryToBuscarPeloId(customerId);
		} catch (final Exception e) {
			throw ClienteRepositoryException.build(e);
		}
	}
	
	private Cliente tryToBuscarPeloId(final String customerId) throws CustomerServiceException {
		final ObjectFactory objectFactory = new ObjectFactory();
		
		final CustomerServiceRequestType customerServiceRequest = objectFactory.createCustomerServiceRequestType();
		customerServiceRequest.setCustomerId(customerId);
		
		final CustomerServiceResponseType customerServiceResponse = customerService.getCustomerInfo(customerServiceRequest);
		return ClienteMapper.from(customerServiceResponse);
	}
	
	private static class ClienteMapper {
		
		public static Cliente from(final CustomerServiceResponseType customerServiceResponse) {
			return map(customerServiceResponse);
		}
		
		private static Cliente map(final CustomerServiceResponseType customerServiceResponse) {
			Cliente cliente = null;

			if (customerServiceResponse.getCustomer() != null) {
				final String nome = customerServiceResponse.getCustomer().getName();
				final String sexo = (customerServiceResponse.getCustomer().getSex() != null)? customerServiceResponse.getCustomer().getSex().toString(): null;
				final Calendar dataNasc = customerServiceResponse.getCustomer().getBirthday();
				final Endereco endereco = map(customerServiceResponse.getCustomer().getAddress());
				final String telefone = customerServiceResponse.getCustomer().getTelephone();
				
				cliente = new Cliente(nome, sexo, dataNasc, telefone, endereco);
			}	

			return cliente;
		}
		
		private static Endereco map(final AddressType addressType) {
			Endereco endereco = null;

			if (addressType != null) {
				final String cidade = addressType.getCity();
				final String rua = addressType.getStreet();
				final String cep = addressType.getZipCode();
				
				endereco = new Endereco(cidade, rua, cep);
			}
			
			return endereco;
		}
	}

}
