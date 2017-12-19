package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import controllers.AbstractController;
import domain.Configuration;

@Controller
@RequestMapping("/administrator")
public class ConfigurationAdministratorController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private ConfigurationService configurationService;
	
	// Constructors ---------------------------------------------------------

	public ConfigurationAdministratorController() {
		super();
	}
	
	// Editing ---------------------------------------------------------------
	
	@RequestMapping(value = "/configuration", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Configuration configuration;

		configuration = configurationService.findOne(2218);
		Assert.notNull(configuration);
		result = this.createEditModelAndView(configuration);

		return result;
	}
	
	@RequestMapping(value = "/configuration", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Configuration configuration,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(configuration, "administrator.params.error");
		else
			try {
				this.configurationService.save(configuration);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(configuration, "administrator.commit.error");
			}

		return res;
	}
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Configuration configuration) {
		ModelAndView result;

		result = this.createEditModelAndView(configuration, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Configuration configuration,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/configuration");
		result.addObject("configuration", configuration);
		result.addObject("message", message);
		return result;
	}
}
