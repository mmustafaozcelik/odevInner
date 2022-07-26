package com.hamitmizrak.atm.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hamitmizrak.atm.sql.dto.BankDto;
import com.hamitmizrak.atm.sql.dto.CustomerDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BankDao implements IDaoConnection<BankDto> {
	
	// CREATE
	@Override
	public void create(BankDto banDto) {
		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false); // transaction
			String sql = "insert into  bank (bank_name,branch_name) values (?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banDto.getBankName());
			preparedStatement.setString(2, banDto.getBranchName());
			int rowEffected = preparedStatement.executeUpdate();
			if (rowEffected > 0) {
				log.info(BankDto.class + " Ekleme Ba�ar�l�");
				connection.commit(); // transaction
			} else {
				log.error(BankDto.class + " !!!! Ekleme Ba�ar�s�z");
				connection.rollback(); // transaction
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! Ekleme s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
	}
	
	// UPDATE
	@Override
	public void update(BankDto banDto) {
		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false); // transaction
			String sql = "update bank set bank_name=?,branch_name=? where bank_id=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banDto.getBankName());
			preparedStatement.setString(2, banDto.getBranchName());
			preparedStatement.setLong(3, banDto.getId());
			int rowEffected = preparedStatement.executeUpdate();
			if (rowEffected > 0) {
				log.info(BankDto.class + " G�ncelleme Ba�ar�l�");
				connection.commit(); // transaction
			} else {
				log.error(BankDto.class + " !!!! G�ncelleme Ba�ar�s�z");
				connection.rollback(); // transaction
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! G�ncelleme s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
	}
	
	// DELETE
	@Override
	public void delete(BankDto banDto) {
		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false); // transaction
			String sql = "delete from  bank  where bank_id=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, banDto.getId());
			int rowEffected = preparedStatement.executeUpdate();
			if (rowEffected > 0) {
				log.info(BankDto.class + " Silme Ba�ar�l�");
				connection.commit(); // transaction
			} else {
				log.error(BankDto.class + " !!!! Silme Ba�ar�s�z");
				connection.rollback(); // transaction
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! Silme s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
		
	}
	
	// L�ST
	// SELECT
	@Override
	public ArrayList<BankDto> list() {
		ArrayList<BankDto> list = new ArrayList<BankDto>();
		BankDto bankDto;
		
		try (Connection connection = getInterfaceConnection()) {
			String sql = "select *  from  bank;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				bankDto = new BankDto();
				bankDto.setId(resultSet.getLong("bank_id"));
				bankDto.setBankName(resultSet.getString("bank_name"));
				bankDto.setBranchName(resultSet.getString("branch_name"));
				bankDto.setCreatedDate(resultSet.getDate("created_date"));
				list.add(bankDto);
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! Silme s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<BankDto> innerJo() {
		BankDao bankDao = new BankDao();
		ArrayList<BankDto> list = new ArrayList<BankDto>();
		BankDto bankDto;
		try (Connection connection = getInterfaceConnection()) {
			String sql = "select * from bank b1 inner join customer as c1 on c1.bank_id = b1.bank_id where b1.bank_id=3;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				bankDto = new BankDto();
				bankDto.setId(resultSet.getLong("bank_id"));
				bankDto.setBankName(resultSet.getString("bank_name"));
				bankDto.setBranchName(resultSet.getString("branch_name"));
				bankDto.setCreatedDate(resultSet.getDate("created_date"));
				bankDto.setCustomerList(bankDao.getCustomerList());
				list.add(bankDto);
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! select s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<CustomerDto> getCustomerList() {
		ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();
		CustomerDto customerDto;
		
		try (Connection connection = getInterfaceConnection()) {
			String sql = "select * from customer where bank_id=3;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				customerDto = new CustomerDto();
				customerDto.setId(resultSet.getLong("customer_id"));
				customerDto.setCustomerName(resultSet.getString("customer_name"));
				customerDto.setCustomerSurName(resultSet.getString("customer_surname"));
				customerDto.setCustomerIdentity(resultSet.getString("customer_identity"));
				customerDto.setCreatedDate(resultSet.getDate("created_date"));
				list.add(customerDto);
			}
		} catch (Exception e) {
			log.error(BankDto.class + " !!!! select s�ras�nda hata meydana geldi");
			e.printStackTrace();
		}
		return list;
	}
	
}
