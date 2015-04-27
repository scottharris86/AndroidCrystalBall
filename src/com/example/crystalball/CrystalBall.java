package com.example.crystalball;

import java.util.Random;

public class CrystalBall
{
	public String[] mAnswers={"It is certain", "It is decidedly so", "All signs say YES",
			"The stars are not aligned", "My reply is no", "It is doubtful",
			"Better not tell you now", "Concentrate and ask again", "Unable to answer now",
	"It is hard to say"};

	public String getAnAnswer()
	{
		
		String answer="";
		
		//randomly set text yes, no, maybe
		Random randomGenerator = new Random();
		int randomNumber=randomGenerator.nextInt(mAnswers.length);
		
		//check random number and assign yes, no maybe
		answer=mAnswers[randomNumber];
		
		return answer;
	}

}
