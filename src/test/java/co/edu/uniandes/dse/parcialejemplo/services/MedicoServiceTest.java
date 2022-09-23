package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)

public class MedicoServiceTest {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private TestEntityManager entityManager;

    private List<MedicoEntity> ltMedicos = new ArrayList<>();
    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp () {
        clearData();
        insertData();
    }
    private void clearData()
    {
        entityManager.getEntityManager().createQuery("Delete from MedicoEntity").executeUpdate();
       
    }

    private void insertData()
    {
        for(int i = 0; i < 5; i++){
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medicoEntity);
            ltMedicos.add(medicoEntity);
        }
        MedicoEntity medicoEntity = ltMedicos.get(2);
        entityManager.persist(medicoEntity);
    }

    @Test
    void testGetMedicos()
    {
        List<MedicoEntity> medicos = medicoService.getMedicos();
        assertEquals(medicos.size(), ltMedicos.size());
        for(MedicoEntity medEnt: medicos)
        {
            boolean found = false;
            for(MedicoEntity storedEntity: ltMedicos)
            {
                if(medEnt.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }


}
