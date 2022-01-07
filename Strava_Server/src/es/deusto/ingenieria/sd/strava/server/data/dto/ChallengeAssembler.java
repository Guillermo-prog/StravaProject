package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

public class ChallengeAssembler {
	private static ChallengeAssembler challengeAssembler = new ChallengeAssembler();
	
	public static ChallengeAssembler getInstance() {
		return challengeAssembler;
	}
	public ChallengeDTO challengeToDTO(Challenge chal) {
		ChallengeDTO dto = new ChallengeDTO();
		
		dto.setTitle(chal.getTitle());
		dto.setSport(chal.getSport());
		dto.setStart(chal.getStart());
		dto.setEnd(chal.getEnd());
		dto.setTargetDistance(chal.getTargetDistance());
		dto.setTargetTime(chal.getTargetTime());
		
		return dto;
	}
	public List<ChallengeDTO> challengeToDTO(List<Challenge> challs){
		List<ChallengeDTO> dtos = new ArrayList<ChallengeDTO>();
		
		for (Challenge chall : challs) {
			dtos.add(this.challengeToDTO(chall));
		}
		
		return dtos;
 	}
	
}
