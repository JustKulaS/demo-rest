package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;

@Service
@Scope("singleton")
public class PaymentMethodImpl implements PaymentMethodService {

	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		validate(entity);
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		if(paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("EL paymentMethod con id: " + entity.getPayId() + "no existe");
		}
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(PaymentMethod entity) throws Exception {
		if(entity==null) {
			throw new Exception("EL paymentMethod es nulo");
		}
		if(entity.getPayId()==null || entity.getPayId()<1) {
			throw new Exception("EL ID del payment es obligatorio");
		}
		if(paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("EL paymentMethod con id: " + entity.getPayId() + "no existe");
		}
		paymentMethodRepository.deleteById(entity.getPayId());
		
	}

	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if(id==null || id < 0) {
			throw new Exception("EL ID del payment es obligatorio");
		}
		if(paymentMethodRepository.existsById(id)) {
			delete(paymentMethodRepository.findById(id).get());
		}
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
		return paymentMethodRepository.findById(id);
	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		if(entity==null) {
			throw new Exception("EL payment es nulo");
		}
		if(entity.getName()==null || entity.getName().isBlank()==true) {
			throw new Exception("EL nombre es obligatorio");
		}
		if(entity.getEnable()==null || entity.getEnable().isBlank()==true) {
			throw new Exception("EL enable es obligatorio");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return paymentMethodRepository.count();
	}

}
