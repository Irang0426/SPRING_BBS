package com.bbs.demo.service;

import java.util.Arrays;
import java.util.List;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

public class Tokenizer {
	List<String> allowPos = Arrays.asList(
		    "NNG", // 일반 명사
		    "NNP", // 고유 명사
		    "NNB", // 의존 명사
		    "NR",  // 수사
		    "NP",  // 대명사
		    "VV",  // 동사
		    "VA",  // 형용사
		    "VX",  // 보조 용언
		    "VCP", // 긍정 지정사
		    "VCN", // 부정 지정사
		    "MM",  // 관형사
		    "MAG", // 일반 부사
		    "MAJ", // 접속 부사
		    "IC",  // 감탄사
		    "SN",  // 숫자
		    "SL"   // 알파벳
		);

	
	public List<String> tokenizer(String content) {
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		
		KomoranResult result = komoran.analyze(content);
		
		List<String> tokenList = result.getMorphesByTags(allowPos);
		
		return tokenList;
	}
}
