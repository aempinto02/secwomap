package br.com.secwomap.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.secwomap.domain.Incident;
import br.com.secwomap.domain.Location;
import br.com.secwomap.repository.IncidentRepository;
import br.com.secwomap.repository.LocationRepository;

@Service
public class DBService {

	@Autowired
	private IncidentRepository repository;
	
	@Autowired
	private LocationRepository locationRepository;

	public void instantiateTestDatabase() {
		List<String> addresses = new ArrayList<>();
		addresses.add("R. Oscar Freire, São Paulo - SP, Brazil");
		addresses.add("Av. Sen. Roberto Símonsen, 828 - Santo Antônio, São Caetano do Sul - SP, 09530-401, Brazil");
		addresses.add("164 Townsend St, San Francisco, CA 94107, USA");
		addresses.add("Quinta da Boa Vista - São Cristóvão, Rio de Janeiro - RJ, 20940-040, Brazil");
		addresses.add("R. São João - Jardim Sao Francisco, São Paulo - SP, 08390, Brazil");
		addresses.add("R. Ipiranga - Campo Belo, São Paulo - SP, 04633-000, Brazil");
		addresses.add("Av. des Champs-Élysées, 75008 Paris, France");
		addresses.add("Lombard St, London, UK");
		addresses.add("R. Ipanema - Barra da Tijuca, Rio de Janeiro - RJ, 22640-100, Brazil");
		addresses.add("R. Marquês de São Vicente, 225 - Gávea, Rio de Janeiro - RJ, 22541-041, Brazil");
		addresses.add("R. Recife - Santo Amaro, Recife - PE, 50030-230, Brazil");
		addresses.add("Alameda América - Ponta Negra, Manaus - AM, 69038, Brazil");
		addresses.add("R. Teresina, Parnaíba - PI, 64218-680, Brazil");
		addresses.add("R. José Veríssimo, 333 - Tarumã, Curitiba - PR, 82820-000, Brazil");
		addresses.add("R. Mário Palmério, 74 - Jardim Santo Antonio, Francisco Morato - SP, 07916-050, Brazil");

		List<String> descriptions = new ArrayList<>();
		descriptions.add("Foi uma noite horrível com várias cervejas jogadas em mim por estar de saia");
		descriptions.add("Mexeram comigo na rua escura quando estava sozinha");
		descriptions.add("Piscaram muito para mim, fiquei com medo");
		descriptions.add("Era fim de jogo e saíram bêbados e agressivos, fui estuprada");
		descriptions.add("Tentaram me enganar para talvez ganhar algum dinheiro ou fazer algo pior, mas saí antes");
		descriptions.add("Me xingaram e ameaçaram bater por ser trans");
		descriptions.add("Olharam muito e falaram algumas coisas desagradáveis");
		descriptions.add("Fui viajar a trabalho e me atacaram na rua mais conhecida da cidade");
		descriptions.add("Dois homens em um carro me cercaram e estava na calçada porém eles apenas queriam me intimidar, não sofri nenhum ataque ou lesão");
		descriptions.add("Tive que me esconder porque além de tiros eles tentaram fazer alguma violência, mas consegui escapar");
		descriptions.add("Depois de uma confusão e briga, um homem me assediou");

		Map<String, Location> enderecos = new HashMap<>();
		Location location = new Location(null, new BigDecimal(-23.56914714013010), new BigDecimal(-46.67914714013010));
		Location location1 = new Location(null, new BigDecimal(-23.62914714013010), new BigDecimal(-46.58914714013010));
		Location location2 = new Location(null, new BigDecimal(37.78914714013010), new BigDecimal(-122.39914714013010));
		Location location3 = new Location(null, new BigDecimal(-22.91914714013010), new BigDecimal(-43.23914714013010));
		Location location4 = new Location(null, new BigDecimal(-23.63914714013010), new BigDecimal(-46.45914714013010));
		Location location5 = new Location(null, new BigDecimal(-23.64914714013010), new BigDecimal(-46.67914714013010));
		Location location6 = new Location(null, new BigDecimal(48.87914714013010), new BigDecimal(2.30914714013010));
		Location location7 = new Location(null, new BigDecimal(51.51914714013010), new BigDecimal(-0.09914714013010));
		Location location8 = new Location(null, new BigDecimal(-23.00914714013010), new BigDecimal(-43.36914714013010));
		Location location9 = new Location(null, new BigDecimal(-22.98914714013010), new BigDecimal(-43.23914714013010));
		Location location10 = new Location(null, new BigDecimal(-8.04914714013010), new BigDecimal(-34.87914714013010));
		Location location11 = new Location(null, new BigDecimal(-3.08914714013010), new BigDecimal(-60.09914714013010));
		Location location12 = new Location(null, new BigDecimal(-2.91914714013010), new BigDecimal(-41.78914714013010));
		Location location13 = new Location(null, new BigDecimal(-25.43914714013010), new BigDecimal(-49.21914714013010));
		Location location14 = new Location(null, new BigDecimal(-23.28914714013010), new BigDecimal(-46.76914714013010));

		enderecos.put(addresses.get(0), location);
		enderecos.put(addresses.get(1), location1);
		enderecos.put(addresses.get(2), location2);
		enderecos.put(addresses.get(3), location3);
		enderecos.put(addresses.get(4), location4);
		enderecos.put(addresses.get(5), location5);
		enderecos.put(addresses.get(6), location6);
		enderecos.put(addresses.get(7), location7);
		enderecos.put(addresses.get(8), location8);
		enderecos.put(addresses.get(9), location9);
		enderecos.put(addresses.get(10), location10);
		enderecos.put(addresses.get(11), location11);
		enderecos.put(addresses.get(12), location12);
		enderecos.put(addresses.get(13), location13);
		enderecos.put(addresses.get(14), location14);

		Random random = new Random();
		for(int i = 0; i < 2000; i++) {
			int randomAddress = random.nextInt(14);
			int randomDescription = random.nextInt(11);
			int randomSeverity = 0;
			while(randomSeverity == 0) {
				randomSeverity = random.nextInt(4);
			}
			
			String endereco = addresses.get(randomAddress);
			Location locationInsert = enderecos.get(endereco);
			locationInsert = locationRepository.save(locationInsert);
			
			Incident incident = new Incident(null, endereco, locationInsert, randomSeverity, descriptions.get(randomDescription));
			repository.save(incident);
		}
	}
}
