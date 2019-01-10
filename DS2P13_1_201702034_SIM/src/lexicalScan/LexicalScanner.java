package lexicalScan;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import app.AppView;
import lexicalScan.Token.TokenType;

public class LexicalScanner {
	
	private boolean _tokenIsAvailable = false;
	private State _currenState;
	private Token _availableToken;
	private Token _token;
	
	//getter/setter
	private boolean tokenIsAvailable() {
		return this._tokenIsAvailable;
	}
	
	private void setTokenIsAvailable(boolean aAvailable) {
		this._tokenIsAvailable = aAvailable;
	}
	
	private State currentState() {
		return this._currenState;
	}
	
	private void setCurrentState(State aState) {
		this._currenState = aState;
	}
	
	private Token availableToken() {
		return this._availableToken;
	}
	
	private void setAvailableToken(Token aToken) {
		this._availableToken = aToken;
	}
	
	private Token token() {
		return this._token;
	}
	
	private void setToken(Token aToken) {
		this._token = aToken;
	}
	
	private BufferedInputStream _bufferedInputStream;
	
	//getter/setter
	private BufferedInputStream bInputStream() {
		return this._bufferedInputStream;
	}
	
	private void setBInputStream(BufferedInputStream aBInputStream) {
		this._bufferedInputStream = aBInputStream;
	}
	
	//Enum && EnumTable
	enum CharType{
		CHAR_Letter, CHAR_Dight, CHAR_Dot, CHAR_Sign, CAHR_DoubleQuote, CHAR_Return, CHAR_TheOthers, CHAR_EOF
	};
	
	//typedef enum {TOKEN_ID< TKOEN_Int, TOKEN_Float, TOKEN_String} TokenType;
	//Token 선언
	
	enum State{
		STATE_Start, STATE_ID, STATE_Int, STATE_Float, STATE_String, STATE_Dot, STATE_End
	};
	
	enum Action{
		ACTION_IDBegin, ACTION_IntBegin, ACTION_FloatBegin, ACTION_StringBegin, ACTION_TokenEnd, 
		ACTION_TokenEndIDBegin, ACTION_TokenEndStringBegin, ACTION_AddToToken, ACTION_AddToTokenIntToFloat, ACTION_None
	};

	State _stateTransitionTable[][] = {
		{State.STATE_ID, State.STATE_Int, State.STATE_Dot,
			State.STATE_Start, State.STATE_String, State.STATE_Start,
			State.STATE_Start, State.STATE_End},
		
		{State.STATE_ID, State.STATE_ID, State.STATE_Start,
			State.STATE_Start, State.STATE_String, State.STATE_Start,
			State.STATE_Start, State.STATE_End},
		
		{State.STATE_ID, State.STATE_Int, State.STATE_Float,
			State.STATE_Start, State.STATE_String, State.STATE_Start,
			State.STATE_Start, State.STATE_End},
		
		{State.STATE_ID, State.STATE_Float, State.STATE_Start,
				State.STATE_Start, State.STATE_String, State.STATE_Start,
				State.STATE_Start, State.STATE_End},
		
		{State.STATE_String, State.STATE_String, State.STATE_String,
				State.STATE_String, State.STATE_Start, State.STATE_String,
				State.STATE_Start, State.STATE_End},
		
		{State.STATE_ID, State.STATE_Float, State.STATE_Dot,
					State.STATE_Start, State.STATE_String, State.STATE_Start,
					State.STATE_Start, State.STATE_End}
		};
	
	Action _actionTable[][] = {
			{Action.ACTION_IDBegin, Action.ACTION_IntBegin,
				Action.ACTION_FloatBegin, Action.ACTION_None,
				Action.ACTION_StringBegin, Action.ACTION_None,
				Action.ACTION_None, Action.ACTION_None},
			
			{Action.ACTION_AddToToken, Action.ACTION_AddToToken,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEndStringBegin, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd},
			
			{Action.ACTION_TokenEndIDBegin, Action.ACTION_AddToToken,
				Action.ACTION_AddToTokenIntToFloat, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEndStringBegin, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd},
			
			{Action.ACTION_TokenEndIDBegin, Action.ACTION_AddToToken,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEndStringBegin, Action.ACTION_TokenEnd,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd},
			
			{Action.ACTION_AddToToken, Action.ACTION_AddToToken,
				Action.ACTION_AddToToken, Action.ACTION_AddToToken,
				Action.ACTION_TokenEnd, Action.ACTION_AddToToken,
				Action.ACTION_TokenEnd, Action.ACTION_TokenEnd},
			
			{Action.ACTION_IDBegin, Action.ACTION_FloatBegin,
				Action.ACTION_None, Action.ACTION_None,
				Action.ACTION_StringBegin, Action.ACTION_None,
				Action.ACTION_None, Action.ACTION_None} 	
	};
	
	//생성자
	public LexicalScanner(String aFileName) {
		try {
			this.setBInputStream(new BufferedInputStream(new FileInputStream(aFileName)));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.setCurrentState(State.STATE_Start);
		this.setTokenIsAvailable(false);
		this.setAvailableToken(null);
		this.setToken(new Token());
	}
	
	private Action[][] actionTable(){
		return this._actionTable;
	}
	
	private State[][] stateTransitionTable(){
		return this._stateTransitionTable;
	}
	
	//공개 함수
	public Token nextToken() throws IOException {
		while(!(this.currentState() == State.STATE_End
				|| this.tokenIsAvailable())) {
			this.transitToNextStateAndTakeAction();
		}
		if(this.tokenIsAvailable()) {
			this.setTokenIsAvailable(false);
			return this.availableToken();
		}
		return null;
	}
	
	//비공개 함수
	private String readCharFromFile() throws IOException {
		Integer readValue = this.bInputStream().read();
		
		if(readValue == -1) {
			return null;
		}
		
		String value = Character.toString((char)(readValue.intValue()));
		if(value.equals("\n")) {
			//AppView.outputLine("DEBUG: '\n'");
		}
		return value;
	}
	
	private CharType charType(String inputString) {
		if(inputString == null) {
			return CharType.CHAR_EOF;
		}
		
		char inputChar = inputString.charAt(0);
		if((inputChar >= 'a' && inputChar <= 'z')
				|| (inputChar >= 'A' && inputChar <= 'Z')
				|| (inputChar == '_')) {
			return CharType.CHAR_Letter;
		}
		else if((inputChar >= '0' && inputChar <= '9')) {
			return CharType.CHAR_Dight;
		}
		else if (inputChar == '.') {
			return CharType.CHAR_Dot;
		}
		else if (inputChar == '+' || inputChar == '-') {
			return CharType.CHAR_Sign;
		}
		else if (inputChar == '"') {
			return CharType.CAHR_DoubleQuote;
		}
		else if (inputChar == '\n') {
			return CharType.CHAR_Return;
		}
		else {
			return CharType.CHAR_TheOthers;
		}
	}
	
	private void transitToNextStateAndTakeAction() throws IOException {
		String inputChar = readCharFromFile();
		CharType inputCharType = charType(inputChar);
		
		State nextState = this.stateTransitionTable()[this.currentState().ordinal()][inputCharType.ordinal()];
		Action actionToBeTaken = this.actionTable()[this.currentState().ordinal()][inputCharType.ordinal()];
		
		switch (actionToBeTaken) {
		case ACTION_IDBegin:
			this.token().clearValue();
			this.token().setTokenType(TokenType.TOKEN_ID);
			this.token().addChar(inputChar);
			break;
		case ACTION_IntBegin:
			this.token().clearValue();
			this.token().setTokenType(TokenType.TIOKEN_Int);
			this.token().addChar(inputChar);
			break;
		case ACTION_FloatBegin:
			this.token().clearValue();
			this.token().setTokenType(TokenType.TOKEN_Float);
			this.token().addChar(inputChar);
			break;
		case ACTION_StringBegin:
			this.token().clearValue();
			this.token().setTokenType(TokenType.TOKEN_String);
			break;
		case ACTION_TokenEnd:
			setAvailableToken(this.token().copy());
			setTokenIsAvailable(true);
			break;
		case ACTION_TokenEndIDBegin:
			setAvailableToken(this.token().copy());
			setTokenIsAvailable(true);
			this.token().clearValue();
			this.token().setTokenType(TokenType.TOKEN_ID);
			this.token().addChar(inputChar);
			break;
		case ACTION_TokenEndStringBegin:
			setAvailableToken(this.token().copy());
			setTokenIsAvailable(true);
			this.token().clearValue();
			this.token().setTokenType(TokenType.TOKEN_String);
			break;
		case ACTION_AddToToken:
			this.token().addChar(inputChar);
			break;
		case ACTION_AddToTokenIntToFloat:
			this.token().addChar(inputChar);
			this.token().setTokenType(TokenType.TOKEN_Float);
			break;
		case ACTION_None:
			break;
		}
		setCurrentState(nextState);
	}
	
}
