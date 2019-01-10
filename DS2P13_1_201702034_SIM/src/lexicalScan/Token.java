package lexicalScan;

import dictionary.Key;

public class Token {
	public enum TokenType {TOKEN_NULL, TOKEN_ID, TIOKEN_Int, TOKEN_Float, TOKEN_String};
	private static final int MAX_TOKEN_VALUE_LENGTH = 255;
	
	private TokenType _tokenType;
	private String _tokenValue;
	
	//getter/setter
	public TokenType tokenType() {
		return this._tokenType;
	}
	
	public void setTokenType(TokenType aTokenType) {
		this._tokenType = aTokenType;
	}
	
	public String tokenValue() {
		return this._tokenValue;
	}
	
	private void setTokenValue(String aTokenValue) {
		this._tokenValue = aTokenValue;
	}
	
	//생성자
	public Token() {
		this.setTokenType(TokenType.TOKEN_NULL);
		this.setTokenValue(null);
	}
	
	public Token(TokenType givenTokenType, String givenTokenValue) {
		this.setTokenType(givenTokenType);
		this.setTokenValue(givenTokenValue);
	}
	
	//공개함수
	public void clearValue() {
		this.setTokenValue("");
	}
	
	public void addChar(String aChar) {
		if(this.tokenValue().length() < MAX_TOKEN_VALUE_LENGTH -1) {
			this.setTokenValue(this.tokenValue() + aChar);
		}
	}

	public Token copy() {
		return new Token(this.tokenType(), this.tokenValue());
	}
	
	//static 함수
	public static Key KeyFromToken(Token token) {
		String tokenValue = token.tokenValue();
		Key newKey = new Key();
		
		newKey.setValue(tokenValue);
		return newKey;
	}
}
