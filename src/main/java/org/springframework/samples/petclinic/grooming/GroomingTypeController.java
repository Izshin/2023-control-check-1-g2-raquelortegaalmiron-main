package org.springframework.samples.petclinic.grooming;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/groomingtypes")
public class GroomingTypeController {
    GroomingTypeService groomingTypeService;

    @Autowired
	public GroomingTypeController(GroomingTypeService groomingTypeService) {
		this.groomingTypeService = groomingTypeService;
	}

    @GetMapping
    @Transactional(readOnly=true)
	public ResponseEntity<List<GroomingType>> findAllGroomingTypes() {
		
		return new ResponseEntity<>(groomingTypeService.getAllGroomingTypes(), HttpStatus.OK);
	}

    @GetMapping(value = "/{gTypeId}")
    public ResponseEntity<GroomingType> findGroomingTypesById(@PathVariable("gTypeId") int gTypeId) {
		GroomingType gType=groomingTypeService.getGroomingTypeById(gTypeId);
        if(gType==null){
            throw new  ResourceNotFoundException("Gtype not found");
        }
        else{
            return new ResponseEntity<>(gType, HttpStatus.OK);

        }
	}

@PostMapping
public ResponseEntity<GroomingType> create(@Valid @RequestBody GroomingType groomingType) {
		
        groomingTypeService.save(groomingType);
		return new ResponseEntity<>(groomingType, HttpStatus.CREATED);
	}

}
