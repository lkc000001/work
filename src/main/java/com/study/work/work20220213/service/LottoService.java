package com.study.work.work20220213.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	private static List<Set<Integer>> lottos = new ArrayList<>();
	
	
	public List<Set<Integer>> getLottos(){
		return lottos;
	}
	
	public List<String> getLottoNumSum(){
		List<String> lottoNumSum = new ArrayList<>();
		List<Integer> lottoNum = new ArrayList<>();
		//取出全部號碼
		lottos.forEach(n ->  n.forEach(n1 -> lottoNum.add(n1)));
		//計算每個號碼的次數
		Map<Integer, Long> lottoMap = lottoNum.stream()
				.collect(Collectors.groupingBy(Integer::valueOf, Collectors.counting()));
		//排序
		Map<Integer, Long> lottoMapSort = lottoMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oVal, nVal) -> oVal, LinkedHashMap::new));
		//轉換格式
		for(Integer key: lottoMapSort.keySet()) {
			lottoNumSum.add(key + ":(" + lottoMapSort.get(key) + "),");
		}	
		System.out.println(lottoNumSum);
		return lottoNumSum;
	}
	
	public void addLotto() {
		lottos.add(0, generateLotto());
	}
	
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
	}
	
	public void deleteLotto(int index) {
		lottos.remove(index);
	}
	private Set<Integer> generateLotto(){
		Random r = new Random();
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39)+1);
		}
		return lotto;
	}

}
