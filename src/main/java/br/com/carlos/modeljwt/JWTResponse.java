package br.com.carlos.modeljwt;

public class JWTResponse {
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwtResponse) {
		this.jwt = jwtResponse;
	}

	public JWTResponse(String jwtResponse) {
		super();
		this.jwt = jwtResponse;
	}

	public JWTResponse() {
	}
	

}
