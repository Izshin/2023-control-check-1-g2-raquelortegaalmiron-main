package org.springframework.samples.petclinic.grooming;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {
    private CouponRepository cr;
    

    @Autowired
    public CouponService(CouponRepository cr){
        this.cr=cr;
    }


    @Transactional
    public Coupon save(Coupon d) {
        Coupon savedCoupon=cr.save(d);
        return savedCoupon;
    }

    @Transactional(readOnly=true)
    public List<Coupon> getAll() {
        List<Coupon> allCoupons= cr.findAll();
        return allCoupons;
    }    

    public void addVisit(Coupon c, Visit v) throws UnfeasibleCouponException {
        // TODO: Change this
    }
}
