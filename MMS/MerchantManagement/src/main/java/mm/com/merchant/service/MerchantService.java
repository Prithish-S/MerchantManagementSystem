package mm.com.merchant.service;

import java.util.List;

import mm.com.merchant.dto.MerchantDTO;
import mm.com.merchant.dto.PosDTO;

public interface MerchantService {
	public List<MerchantDTO> getMerchantList();
	public MerchantDTO getMerchant(String merchant_id);
	public MerchantDTO addMerchant(MerchantDTO merchantDTO)throws Exception ; //to add a user
	public List<PosDTO> getPosList(String MerchantId);
	public PosDTO addMerchantPos(PosDTO posDTO) throws Exception;//to add a Pos
}
