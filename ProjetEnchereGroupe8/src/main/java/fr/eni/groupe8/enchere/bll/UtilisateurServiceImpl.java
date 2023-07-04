package fr.eni.groupe8.enchere.bll;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.UtilisateurDAO;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;

	private final PasswordEncoder passwordEncoder;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public List<Utilisateur> getListUtilisateur() {

		return utilisateurDAO.findAllUtilisateur();
	}

	@Override
	public Utilisateur findUtilisateurById(Integer noUtilisateur) {

		return utilisateurDAO.readUtilisateur(noUtilisateur);
	}

	@Override
	public void enregistrerUtilisateurs(Utilisateur utilisateur) {

		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		utilisateurDAO.save(utilisateur);

	}

}
