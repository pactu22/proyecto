/*
 * Created on 14 Apr 2015 ( Time 18:45:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.stories.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.stories.bean.Submission;
import org.stories.bean.jpa.SubmissionEntity;
import java.util.Date;
import java.util.List;
import org.stories.business.service.mapping.SubmissionServiceMapper;
import org.stories.data.repository.jpa.SubmissionJpaRepository;
import org.stories.test.SubmissionFactoryForTest;
import org.stories.test.SubmissionEntityFactoryForTest;
import org.stories.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of SubmissionService
 */
@RunWith(MockitoJUnitRunner.class)
public class SubmissionServiceImplTest {

	@InjectMocks
	private SubmissionServiceImpl submissionService;
	@Mock
	private SubmissionJpaRepository submissionJpaRepository;
	@Mock
	private SubmissionServiceMapper submissionServiceMapper;
	
	private SubmissionFactoryForTest submissionFactoryForTest = new SubmissionFactoryForTest();

	private SubmissionEntityFactoryForTest submissionEntityFactoryForTest = new SubmissionEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer id = mockValues.nextInteger();
		
		SubmissionEntity submissionEntity = submissionJpaRepository.findOne(id);
		
		Submission submission = submissionFactoryForTest.newSubmission();
		when(submissionServiceMapper.mapSubmissionEntityToSubmission(submissionEntity)).thenReturn(submission);

		// When
		Submission submissionFound = submissionService.findById(id);

		// Then
		assertEquals(submission.getId(),submissionFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<SubmissionEntity> submissionEntitys = new ArrayList<SubmissionEntity>();
		SubmissionEntity submissionEntity1 = submissionEntityFactoryForTest.newSubmissionEntity();
		submissionEntitys.add(submissionEntity1);
		SubmissionEntity submissionEntity2 = submissionEntityFactoryForTest.newSubmissionEntity();
		submissionEntitys.add(submissionEntity2);
		when(submissionJpaRepository.findAll()).thenReturn(submissionEntitys);
		
		Submission submission1 = submissionFactoryForTest.newSubmission();
		when(submissionServiceMapper.mapSubmissionEntityToSubmission(submissionEntity1)).thenReturn(submission1);
		Submission submission2 = submissionFactoryForTest.newSubmission();
		when(submissionServiceMapper.mapSubmissionEntityToSubmission(submissionEntity2)).thenReturn(submission2);

		// When
		List<Submission> submissionsFounds = submissionService.findAll();

		// Then
		assertTrue(submission1 == submissionsFounds.get(0));
		assertTrue(submission2 == submissionsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Submission submission = submissionFactoryForTest.newSubmission();

		SubmissionEntity submissionEntity = submissionEntityFactoryForTest.newSubmissionEntity();
		when(submissionJpaRepository.findOne(submission.getId())).thenReturn(null);
		
		submissionEntity = new SubmissionEntity();
		submissionServiceMapper.mapSubmissionToSubmissionEntity(submission, submissionEntity);
		SubmissionEntity submissionEntitySaved = submissionJpaRepository.save(submissionEntity);
		
		Submission submissionSaved = submissionFactoryForTest.newSubmission();
		when(submissionServiceMapper.mapSubmissionEntityToSubmission(submissionEntitySaved)).thenReturn(submissionSaved);

		// When
		Submission submissionResult = submissionService.create(submission);

		// Then
		assertTrue(submissionResult == submissionSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Submission submission = submissionFactoryForTest.newSubmission();

		SubmissionEntity submissionEntity = submissionEntityFactoryForTest.newSubmissionEntity();
		when(submissionJpaRepository.findOne(submission.getId())).thenReturn(submissionEntity);

		// When
		Exception exception = null;
		try {
			submissionService.create(submission);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Submission submission = submissionFactoryForTest.newSubmission();

		SubmissionEntity submissionEntity = submissionEntityFactoryForTest.newSubmissionEntity();
		when(submissionJpaRepository.findOne(submission.getId())).thenReturn(submissionEntity);
		
		SubmissionEntity submissionEntitySaved = submissionEntityFactoryForTest.newSubmissionEntity();
		when(submissionJpaRepository.save(submissionEntity)).thenReturn(submissionEntitySaved);
		
		Submission submissionSaved = submissionFactoryForTest.newSubmission();
		when(submissionServiceMapper.mapSubmissionEntityToSubmission(submissionEntitySaved)).thenReturn(submissionSaved);

		// When
		Submission submissionResult = submissionService.update(submission);

		// Then
		verify(submissionServiceMapper).mapSubmissionToSubmissionEntity(submission, submissionEntity);
		assertTrue(submissionResult == submissionSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = mockValues.nextInteger();

		// When
		submissionService.delete(id);

		// Then
		verify(submissionJpaRepository).delete(id);
		
	}

}
