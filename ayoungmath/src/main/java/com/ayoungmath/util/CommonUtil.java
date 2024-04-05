package com.ayoungmath.util;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class CommonUtil {
	
	/*문자 오름차순*/
	public static List<HashMap<String, Object>> compareTo(List<HashMap<String,Object>> list) {
		
		Collections.sort(list, new Comparator<HashMap<String, Object>>() {
			@Override
			public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
				String name1 = (String) o1.get("COL_NM");
				String name2 = (String) o2.get("COL_NM");
				return name1.compareTo(name2);
			}
		});
		
		return list;
	}
	
	
	/*코드 리스트*/
	public List<HashMap<String, Object>> codeList(List<HashMap<String, Object>>  list, List<HashMap<String, Object>>  codeList) {
		
		for(int j = 0; j < list.size(); j++) {
			//매물유형 분류 길이로 소분류 담기
	//		if(!list.get(j).get("COL_NM_SUB").toString().equals("")) {
				//for문 안에 있어야 함 (메모리문제) HashMap선언
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("COL_CD", list.get(j).get("COL_CD"));
				map.put("COL_NM", list.get(j).get("COL_NM"));
				map.put("COL_NM_SUB", list.get(j).get("COL_NM_SUB"));
				
				codeList.add(map);
	//		}
		}
			
		
		return codeList;
	}
	
	
	
	/*중복데이터 , 추가데이터 추출*/
	public static ArrayList<String> compageAndDel(ArrayList<String> target, ArrayList<String> source) {
	    ArrayList<String> tmpArr = new ArrayList<>();
	    tmpArr.addAll(target);
	    for (String item : source) {
	        if (target.contains(item) == true) {
	            //일치하는 아이템을 지움
	            tmpArr.remove(item);
	        }
	    }
	    return tmpArr;
	}
	
	
	
	
	/*코드 대분류 소분류 NM 출력*/
	public String codeMatchNm(String[] codeList, String colId, List<HashMap<String, Object>>  list) {
		String codeNm = "";
		//System.out.println(Arrays.toString(codeList));
		for(int j = 0; j < list.size(); j++) {
			if(codeList.length == 2){
				if(list.get(j).get("COL_CD").equals(codeList[1])) {
					codeNm = list.get(j).get("COL_NM_SUB").toString();
				}
			}else if(codeList.length == 1){
				if(list.get(j).get("COL_CD").equals(codeList[0])) {
					codeNm = list.get(j).get("COL_NM").toString();
				}
			}
			
		}
		return codeNm;
	}
	
	
	/*이미지 출력 */
	public HashMap<String,Object> imgList(List<HashMap<String, Object>> fileList) {
		
		List<String> fileImgList = new ArrayList<String>();
		HashMap<String,Object> imgMap = new HashMap<String,Object>();
		List<String> pdfFileImgList = new ArrayList<String>();
		//메인 이미지
		String mainFileImg = "";
		//메인이미지 이름
		String mainFileName = "";
		//메인이미지 오리지널이름
		String mainFileOriginName = "";
		
		String mainFileType = "";
		
		for(int i = 0; i < fileList.size(); i++) {
			String fileType = fileList.get(i).get("type").toString();
			String mainImg = fileList.get(i).get("main_img").toString();
			String openYn = fileList.get(i).get("open_yn").toString();
			String fileImgName = fileList.get(i).get("file_name").toString();
			if(!fileType.equals("U")) {
				String fileExt = fileList.get(i).get("file_ext").toString();
				String location = fileImgName + "." + fileExt;
				
				String fileOriginName = fileList.get(i).get("origin_file_name").toString();
				if(fileType.toString().equals("T")) {
					if(fileExt.toLowerCase().equals("png") || fileExt.toLowerCase().equals("jpg")) {
						if(mainImg.equals("Y") && openYn.equals("Y")) {
							mainFileImg = location;
							mainFileName = fileImgName;
							mainFileOriginName = fileOriginName;
						}else if(mainImg.equals("N") && openYn.equals("Y")){
							fileImgList.add(location);
							pdfFileImgList.add(fileOriginName);
						}
					}
				}
			}else { //유튜브일떄
				if(mainImg.equals("Y") && openYn.equals("Y")) {
					mainFileType = fileList.get(i).get("type").toString();
					mainFileImg = fileList.get(i).get("file_name").toString();
				}else if(mainImg.equals("N") && openYn.equals("Y")){
					fileImgList.add(fileList.get(i).get("file_name").toString());
				}
				
			}
			
		}
		
		
		if(fileImgList.size()>0) {
			imgMap.put("fileImgList", fileImgList);
		}
		
		imgMap.put("mainFileImg", mainFileImg);
		imgMap.put("mainFileType", mainFileType);
		imgMap.put("mainFileName", mainFileName);
		imgMap.put("mainFileOriginName", mainFileOriginName);
		imgMap.put("pdfFileImgList", pdfFileImgList);
		
		return imgMap;
	}
	
	
	/*모바일 여부*/
	public static Boolean isDevice(HttpServletRequest req) {
	    String userAgent = req.getHeader("User-Agent").toUpperCase();
	    Boolean mobile = false;
	 
	    if(userAgent.indexOf("MOBILE") > -1) {
	    	mobile = true;
	    }
		return mobile;
		
	}
	
	
	/* 날짜 계산 */
	public static String dtCal() throws Exception {
		SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
		String resultDate = "";
		try {
			Date now = new Date();
			String today = sdfYMD.format(now);
			Date date = sdfYMD.parse(today);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 6); 
			sdfYMD.format(cal.getTime());
			resultDate = sdfYMD.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDate;
		
	}
	
	

	/* 날짜 기한 체크 */
	public static boolean dateChk(String dt) throws Exception {
		SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
		boolean result = false;
		try {
			Date now = new Date();
			String today = sdfYMD.format(now);
			
			Date endDate = sdfYMD.parse(dt);
			Date todate = sdfYMD.parse(today);
			int compare = endDate.compareTo(todate);
			
			if(compare >= 0) {//사용가능
				result = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	/*코드 대분류 소분류 리스트 NM 출력*/
	public List<HashMap<String,Object>> codeMatchNmList(List<String> codeList, String colId, List<HashMap<String, Object>>  list) {
		
		List<HashMap<String,Object>> map = new ArrayList<HashMap<String,Object>>();
		for(int i = 0; i < codeList.size(); i++) {
			HashMap<String,Object> map1 = new HashMap<String, Object>();
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).get("COL_CD").equals(codeList.get(i))) {
					if(!list.get(j).get("COL_NM_SUB").toString().isEmpty()) {
						map1.put("codeNm", list.get(j).get("COL_NM_SUB").toString());
						map1.put("code", codeList.get(i));
					}else {
						map1.put("codeNm", list.get(j).get("COL_NM").toString());
						map1.put("code", codeList.get(i));
					}
					
				}
			}
			map.add(map1);
		}
		
		return map;
	}

	
	
	/*코드Nm 리스트 포함에서 리스트 출력(계약서 코드)*/
	public List<HashMap<String,Object>> codeMatchNmCodeList(List<HashMap<String,Object>> list, String colId1, List<HashMap<String,Object>> list1, String codeNm1,
			String colId2, List<HashMap<String, Object>> list2, String codeNm2) {
	
		String[] nm1;
		String[] nm2;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).get(colId1) != null) {
				nm1 = list.get(i).get(colId1).toString().split(",");
				String[] coNm = new String[nm1.length];
				for(int j = 0; j < nm1.length; j++) {
					for(int z = 0; z < list1.size(); z++) {
						if(nm1[j].equals(list1.get(z).get("COL_CD").toString())) {
							coNm[j] = list1.get(z).get("COL_NM").toString();
						}
					}
				}
				list.get(i).put(codeNm1, String.join(",", coNm));
			}else {
				list.get(i).put(codeNm1,"");
			}
			
			
			if(list.get(i).get(colId2) != null) {
				nm2 = list.get(i).get(colId2).toString().split(",");
				String[] coNm = new String[nm2.length];
				for(int j = 0; j < nm2.length; j++) {
					for(int z = 0; z < list2.size(); z++) {
						if(nm2[j].equals(list2.get(z).get("COL_CD").toString())) {
							coNm[j] = list2.get(z).get("COL_NM").toString();
						}
					}
				}
				list.get(i).put(codeNm2, String.join(",", coNm));
			}else {
				list.get(i).put(codeNm2,"");
			}
			
		}
		
		return list;
	}
	
	/*계약 구분에 제외할 코드 제외 후 추출*/
	public List<HashMap<String,Object>> articleCd(String[] conCdExcept, List<HashMap<String,Object>> list){
		List<HashMap<String,Object>> reList = new ArrayList<HashMap<String,Object>>();
		
		for(int i = 0; i < list.size(); i++) {
			if(!Arrays.asList(conCdExcept).contains(list.get(i).get("COL_CD").toString())) {
				reList.add(list.get(i));
			}
		}
		
		return reList;
	}
	

	
	
	/*계약서 작성 - 계약 종류에 따라 물건 종류 리스트 출력*/
	public List<HashMap<String,Object>> standardObCdList(List<HashMap<String,Object>> list){
		List<HashMap<String,Object>> reList = new ArrayList<HashMap<String,Object>>();
		for(int i = 0; i < list.size(); i++) {
			if("T1".equals(list.get(i).get("COL_TAF").toString())) {
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("COL_NM", list.get(i).get("COL_NM"));
				map.put("COL_CD", list.get(i).get("COL_CD"));
				map.put("COL_CN", list.get(i).get("COL_CN"));
				map.put("COL_TYPE", list.get(i).get("COL_TYPE"));
				
				reList.add(map);
			}
			
		}
		return reList;
	}
	
	
	
	public List<HashMap<String,Object>> conJsonMapList (String json, String type, HashMap<String,Object> accMap) throws Exception{

		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray)parser.parse(json);
		
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonList = (JSONObject) jsonArray.get(i);
			HashMap<String,Object> map = new HashMap<String, Object>();
			if(type == "amt") {
				map.put("amt", jsonList.get("amt"));
				map.put("amtType", jsonList.get("amtType"));
				map.put("desintDate", jsonList.get("desintDate"));
				map.put("vat", jsonList.get("vat"));
				map.put("contrDate", jsonList.get("contrDate"));
				map.put("conCd", jsonList.get("conCd"));
				map.put("ctrtSeq", accMap.get("ctrtSeq"));
				map.put("updateUseq", accMap.get("useq"));
				if(jsonList.get("caSeq") != null && !"".equals(jsonList.get("caSeq")) ) {
					map.put("caSeq", jsonList.get("caSeq"));
				}
			}else if(type == "sp") {
				map.put("odr", jsonList.get("odr"));
				map.put("body", jsonList.get("body"));
				map.put("ctrtSeq", accMap.get("ctrtSeq"));
				map.put("mseq", accMap.get("mseq"));
				if(jsonList.get("spSeq") != null && !"".equals(jsonList.get("spSeq")) ) {
					map.put("spSeq", jsonList.get("spSeq"));
				}
			}else if(type == "per") {
				map.put("conTrader", jsonList.get("conTrader"));
				map.put("name", jsonList.get("name"));
				map.put("bizName", jsonList.get("bizName"));
				map.put("relationIndCorp", jsonList.get("relationIndCorp"));
				map.put("birthCorperNum", jsonList.get("birthCorperNum"));
				map.put("leBizNum", jsonList.get("leBizNum"));
				map.put("addr1", jsonList.get("addr1"));
				map.put("tel", jsonList.get("tel"));
				map.put("ctrtSeq", accMap.get("ctrtSeq"));
				map.put("mseq", accMap.get("mseq"));
				map.put("useq", accMap.get("useq"));
				if(jsonList.get("ctSeq") != null && !"".equals(jsonList.get("ctSeq")) ) {
					map.put("ctSeq", jsonList.get("ctSeq"));
				}
				
			}else if(type == "auth") {
				map.put("openUseq", jsonList.get("useq"));
				map.put("ctrtSeq", accMap.get("ctrtSeq"));
				map.put("crtUseq", accMap.get("useq"));
				if(jsonList.get("cAuthSeq") != null && !"".equals(jsonList.get("cAuthSeq")) ) {
					map.put("cAuthSeq", jsonList.get("cAuthSeq"));
				}
				
			}else if(type == "com") {
				map.put("ctrtSeq", accMap.get("ctrtSeq"));
				map.put("useq", accMap.get("useq"));
				map.put("bsnmCmpnm", jsonList.get("bsnmCmpnm"));
				map.put("brkrNm", jsonList.get("brkrNm"));
				map.put("brkpgRegistNo", jsonList.get("brkpgRegistNo"));
				map.put("addr", jsonList.get("addr"));
				map.put("pnu", jsonList.get("pnu"));
				map.put("registDe", jsonList.get("registDe"));
				map.put("sttusSeCode", jsonList.get("sttusSeCode"));
				map.put("ldCpsgCode", jsonList.get("ldCpsgCode"));
				map.put("ldEmdLiCode", jsonList.get("ldEmdLiCode"));
				map.put("agentName", jsonList.get("agentName"));
				map.put("agentTel", jsonList.get("agentTel"));
				map.put("odr", jsonList.get("odr"));
				if(jsonList.get("reaSeq") != null && !"".equals(jsonList.get("reaSeq")) ) {
					map.put("reaSeq", jsonList.get("reaSeq"));
				}
			}
			list.add(map);	
		}
		
		return list;
	}
	
	public List<HashMap<String,Object>> amtConList(List<HashMap<String,Object>> amtList){
		List<HashMap<String,Object>> amtConList = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map = new HashMap<String, Object>();
		
		//금액분류 ( 1: 매매금액, 2: 계약금, 3 :중도금, 4: 잔금, 5 :보증금, 6 :관리비, 7 :차임(임대료)-월세, 8 :권리금)
		int num = 0;
		for(int i = 0; i < amtList.size(); i++) {
			map = amtList.get(i);
			if("1".equals(map.get("amtType")) || "8".equals(map.get("amtType"))) {
				map.put("conSlsAmt", amtList.get(i).get("amt"));
				map.put("sVSel", amtList.get(i).get("vat"));
				map.put("caSeq1", amtList.get(i).get("caSeq"));
			}
			if("2".equals(map.get("amtType"))) {
				map.put("conDwnPay", amtList.get(i).get("amt"));
				map.put("conPayDt", amtList.get(i).get("contrDate"));//계약금 약정일
				map.put("caSeq2", amtList.get(i).get("caSeq"));
			}
			if("3".equals(map.get("amtType"))) {
				num++;
				map.put("conInterPay", amtList.get(i).get("amt"));
				map.put("conInterPayNum", num);
			}
			if("4".equals(map.get("amtType"))) {
				map.put("conBalPay", amtList.get(i).get("amt"));
				map.put("conLeDt", amtList.get(i).get("contrDate"));//잔금약정일
				map.put("caSeq4", amtList.get(i).get("caSeq"));
			}
			if("5".equals(map.get("amtType"))) {
				map.put("conDeposit", amtList.get(i).get("amt"));
				map.put("caSeq5", amtList.get(i).get("caSeq"));
			}
			if("6".equals(map.get("amtType"))) {
				map.put("conMngmtCost", amtList.get(i).get("amt"));
				map.put("mSel", amtList.get(i).get("desintDate"));
				map.put("mVSel", amtList.get(i).get("vat"));
				map.put("caSeq6", amtList.get(i).get("caSeq"));
			}
			if("7".equals(map.get("amtType"))) {
				map.put("conRent", amtList.get(i).get("amt"));
				map.put("rSel", amtList.get(i).get("desintDate"));
				map.put("rVSel", amtList.get(i).get("vat"));
				map.put("caSeq7", amtList.get(i).get("caSeq"));
			}
			amtConList.add(map);
		}
		
		amtConList = amtConList.stream().sorted((o1, o2) -> o1.get("contrDate").toString().compareTo(o2.get("contrDate").toString()) ).collect(Collectors.toList());
		
		return amtConList;
	}
	
	public String NumberToKor(String amt){
        
        String amt_msg = "";
        String[] arrayNum = {"", "일","이","삼","사","오","육","칠","팔","구"};
        String[] arrayUnit = {"","십","백","천","만","십만","백만","천만","억","십억","백업","천억","조","십조","백조","천조","경","십경","백경","천경","해","십해","백해","천해"};
   
        if(amt.length() > 0){
            int len = amt.length();
            
            String[] arrayStr = new String[len];
            String hanStr = "";
            String tmpUnit = "";
            for(int i = 0; i < len; i++){
                arrayStr[i] = amt.substring(i, i+1);
            }
            int code = len;
            for(int i = 0; i < len; i++){
                code--;
                tmpUnit = "";
                if(!"-".equals(arrayStr[i])) {
                	if(arrayNum[Integer.parseInt(arrayStr[i])] != ""){
                        tmpUnit = arrayUnit[code];
                        if(code > 4){
                            if((Math.floor(code/4) == Math.floor((code-1)/4) 
                            && arrayNum[Integer.parseInt(arrayStr[i+1])] != "") ||
                            (Math.floor(code/4) == Math.floor((code-2)/4)
                            && arrayNum[Integer.parseInt(arrayStr[i+2])] != "")) {
                                tmpUnit = arrayUnit[code].substring(0,1);
                            }
                        }
                    }
                	 hanStr += arrayNum[Integer.parseInt(arrayStr[i])]+tmpUnit;
                }else {
                	hanStr = "";
                }
                
               
            }
            amt_msg = hanStr;
        }else{
            amt_msg = amt;
        }
     
        return amt_msg;
    }
	
	//날짜 년월일로 
	public String DateToKor(String dt){
		
		String formattedDate = "";

		if(dt != null && !"".equals(dt)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate localDate = LocalDate.parse(dt, formatter);

	        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

	        formattedDate = localDate.format(newFormatter);
		}
        
		
		return formattedDate ;
	}
	
	//전화번호, 주민등록번호 하이픈
	public String hypNum(String num,  String type,  String mask){
		String formatNum = "";
		
		if (num == null)
			return "";
		//주민등록번호
		if("jo".equals(type)) {
			if (num.length() <= 0 || (num.length() != 13 && num.length() != 14))
				return num;
			if (num.length() == 13) {
				formatNum = num.substring(0, 6) + "-" + num.substring(6);
			} else if (num.length() == 14) {
				formatNum = num;
			}
		}else {
			if (num.length() == 11) {
		        if (mask.equals("Y")) {
		            formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
		        }else{
		            formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
		        }
		    }else if(num.length()==8){
		        formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
		    }else{
		        if(num.indexOf("02")==0){
		            if(mask.equals("Y")){
		                formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
		            }else{
		                formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
		            }
		        }else{
		            if(mask.equals("Y")){
		                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
		            }else{
		                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
		            }
		        }
		    }
		}

		return formatNum;
	}
	
	
	
	
	/* 숫자,소수점 포함 콤마 */
	public String commaNum(String num) {
		
		if(num != null) {
			if(num.contains(".")) {
				String[] numSpVal = num.split("\\."); 
				String decimalPart = numSpVal[1].replaceAll("0*$", "");
				 if(decimalPart.isEmpty()) {
					 num = numSpVal[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			     }else{
			    	numSpVal[0] = numSpVal[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			    	num = numSpVal[0] + "." + decimalPart;
				//	num = String.join(".", numSpVal);
			     }
			}else {
				num = num.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			}
		}
		return num;
	}
	
	/* 숫자,소수점 포함 콤마 소수점 네자리 */
	public String commaNumWithDecimalLimit(String num) {
		
		if(num != null) {
			if(num.contains(".")) {
				String[] numSpVal = num.split("\\."); 
				String decimalPart = numSpVal[1].replaceAll("0*$", "");
				if(decimalPart.length() > 4) {
	                decimalPart = decimalPart.substring(0, 4);
	            }
				if(decimalPart.isEmpty()) {
					num = numSpVal[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				}else{
					numSpVal[0] = numSpVal[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
					num = numSpVal[0] + "." + decimalPart;
					//	num = String.join(".", numSpVal);
				}
			}else {
				num = num.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			}
		}
		return num;
	}
	
	
	
	
	public String codeNmJoin(String codeValList, List<HashMap<String,Object>> codeList) {
		
		String nmVal = "";
		String[] valList = codeValList.split(",");
		String[] codeNmVal = new String[valList.length];
		if(codeValList != null && !"".equals(codeValList)) {
			for(int i = 0; i < valList.length; i++) {
				for(int j = 0; j < codeList.size(); j++) {
					if(valList[i].equals(codeList.get(j).get("COL_CD").toString())) {
						codeNmVal[i] = codeList.get(j).get("COL_NM").toString();
					}
				}
			}
			
			nmVal = String.join(",", codeNmVal);
		}else {
			nmVal = "";
		}
		
		
		return nmVal;
	}
	
	
	/* 날짜 사이 계산 */
	public int NumOfMonth(String szSDate, String szEDate) {
		
		int month_diff = 0;
		if(!szSDate.isEmpty()) {
			szSDate = szSDate.replace("-", "");
		}
		if(!szEDate.isEmpty()) {
			szEDate = szEDate.replace("-", "");
		}
		if(!szSDate.isEmpty() && !szEDate.isEmpty()) {
			int sYear= Integer.parseInt(szSDate.substring(0,4)); 
			int sMonth = Integer.parseInt(szSDate.substring(4,6)); 

			int eYear = Integer.parseInt(szEDate.substring(0,4)); 
			int eMonth = Integer.parseInt(szEDate.substring(4,6)); 

			month_diff = (eYear - sYear)* 12 + (eMonth - sMonth); 
			
		}
		
		return month_diff;
		
	}
	/* 계약서 거래자 정렬 */	
	public List<HashMap<String,Object>> perOrder(List<HashMap<String,Object>> list){
		
        Collections.sort(list, new Comparator<HashMap<String, Object>>() {
            @Override
            public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
                String role1 = (String) o1.get("conTraderNm");
                String role2 = (String) o2.get("conTraderNm");

                if (role1.substring(0, 2).equals(role2.substring(0, 2))) {
                    return role1.length() - role2.length();
                }
                return role1.compareTo(role2);
            }
        });
		
		
		return list;
		
	}
	
}
