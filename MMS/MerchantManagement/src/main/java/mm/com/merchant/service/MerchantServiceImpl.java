package mm.com.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mm.com.merchant.dto.MerchantDTO;
import mm.com.merchant.dto.PosDTO;
import mm.com.merchant.repository.MerchantRepo;

@Service
public class MerchantServiceImpl implements MerchantService{
	
	@Autowired
	MerchantRepo merchantRepo;
	
	
	@Override
	public MerchantDTO addMerchant(MerchantDTO merchantDTO) throws Exception {
		merchantDTO = merchantRepo.addMerchant(merchantDTO);
		return merchantDTO;
	}

	//This method is used here to view all roles
		@Override
		public List<MerchantDTO> getMerchantList() {
			String methodName="getMerchantList";
			List<MerchantDTO> merchantList = merchantRepo.getMerchantList();
			return merchantList;
		}

		@Override
		public List<PosDTO> getPosList(String MerchantId) {
			String methodName="getPosList";
			List<PosDTO> posList = merchantRepo.getPosList(MerchantId);
			return posList;
		}

		@Override
		public PosDTO addMerchantPos(PosDTO posDTO) throws Exception {
			posDTO = merchantRepo.addMerchantPos(posDTO);
			return posDTO;
		}
		
		public MerchantDTO getMerchant(String merchant_id) {
			return merchantRepo.getMerchant(merchant_id);
		}

}
