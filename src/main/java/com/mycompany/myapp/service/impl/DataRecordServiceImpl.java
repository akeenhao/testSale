package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DataRecordService;
import com.mycompany.myapp.domain.DataRecord;
import com.mycompany.myapp.repository.DataRecordRepository;
import com.mycompany.myapp.service.dto.DataRecordDTO;
import com.mycompany.myapp.service.mapper.DataRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DataRecord}.
 */
@Service
@Transactional
public class DataRecordServiceImpl implements DataRecordService {

    private final Logger log = LoggerFactory.getLogger(DataRecordServiceImpl.class);

    private final DataRecordRepository dataRecordRepository;

    private final DataRecordMapper dataRecordMapper;

    public DataRecordServiceImpl(DataRecordRepository dataRecordRepository, DataRecordMapper dataRecordMapper) {
        this.dataRecordRepository = dataRecordRepository;
        this.dataRecordMapper = dataRecordMapper;
    }

    @Override
    public DataRecordDTO save(DataRecordDTO dataRecordDTO) {
        log.debug("Request to save DataRecord : {}", dataRecordDTO);
        DataRecord dataRecord = dataRecordMapper.toEntity(dataRecordDTO);
        dataRecord = dataRecordRepository.save(dataRecord);
        return dataRecordMapper.toDto(dataRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DataRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataRecords");
        return dataRecordRepository.findAll(pageable)
            .map(dataRecordMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DataRecordDTO> findOne(Long id) {
        log.debug("Request to get DataRecord : {}", id);
        return dataRecordRepository.findById(id)
            .map(dataRecordMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataRecord : {}", id);
        dataRecordRepository.deleteById(id);
    }
    @Override
    public void saveRecord() throws Exception {
        FileInputStream filestream = new FileInputStream("result.txt");
        InputStreamReader readStream = new InputStreamReader(filestream, "gbk");
        BufferedReader reader = new BufferedReader(readStream);

        String temp ;
        int line = 0;//行号
        while ((temp = reader.readLine()) != null) {
            line++;
            System.out.println(line + ":" + temp);
            DataRecordDTO dataRecord = new DataRecordDTO();
            String[] records = temp.split(" ");
            dataRecord.setv1(Integer.valueOf(records[0]));
            dataRecord.setv2(Integer.valueOf(records[1]));
            dataRecord.setv3(Integer.valueOf(records[2]));
            dataRecord.setv4(Integer.valueOf(records[3]));
            dataRecord.setv5(Integer.valueOf(records[4]));
            dataRecord.setv6(Integer.valueOf(records[5]));
            dataRecord.setv7(Integer.valueOf(records[6]));
            this.save(dataRecord);
        }

        if (readStream != null) {
            readStream.close();
        }
        if (reader != null) {
            reader.close();
        }
    }



}
