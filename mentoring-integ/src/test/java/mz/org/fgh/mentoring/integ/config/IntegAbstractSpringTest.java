/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.config;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.LoaderFactory;
import mz.co.mozview.frameworks.core.util.CleanDBUtil;
import mz.co.mozview.frameworks.core.webservices.model.UnitWS;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegApplicationContextTest.class)
@ActiveProfiles("test")
public abstract class IntegAbstractSpringTest {

	@Inject
	private CleanDBUtil cleanDBUtil;

	@BeforeClass
	public static void setUpBeforeClass() {
		RestAssured.port = 8081;
		RestAssured.defaultParser = Parser.JSON;

		LoaderFactory.loadTemplates("mz.org.fgh.mentoring.core.fixturefactory");
	}

	@Before
	public abstract void setUp() throws BusinessException;

	@After
	public void tearDown() {
		this.cleanDBUtil.cleanDB();
	}

	public UserContext getUserContext() {
		final UserContext context = new UserContext();
		context.setId(1L);

		context.setUnit(
				new UnitWS("NCU000001", "102124774", "Office Alima", "Bairro Djuba, Q-2, Casa nr. 375, Matola-Rio",
						"+(258) 82 2546100 ou +(258) 84 0546824", "steliomo@gmail.com"));

		return context;
	}
}
