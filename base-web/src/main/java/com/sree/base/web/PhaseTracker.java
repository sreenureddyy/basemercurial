package com.sree.base.web;

import java.text.MessageFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.sree.base.dao.BaseDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PhaseTracker implements PhaseListener {
	private static final long serialVersionUID = 6358081870120864332L;
	private static final Logger logger = Logger.getLogger(PhaseTracker.class);
	private ThreadLocal<Long> phaseTimer = new ThreadLocal();

	public void afterPhase(PhaseEvent event) {
		Long phaseStartTime = (Long) this.phaseTimer.get();

		long measuredTime = 0L;
		if (phaseStartTime != null) {
			measuredTime = System.currentTimeMillis()
					- phaseStartTime.longValue();
		}

		this.logger.debug(MessageFormat
				.format("Phase {0} completed by {1}ms",
						new Object[] { event.getPhaseId(),
								Long.valueOf(measuredTime) }));
		this.phaseTimer.set(null);
	}

	public void beforePhase(PhaseEvent event) {
		logger.debug(MessageFormat.format("Phase {0} started",
				new Object[] { event.getPhaseId() }));
		this.phaseTimer.set(Long.valueOf(System.currentTimeMillis()));

		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		// Stronger according to blog comment below that references HTTP spec
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		// some date in the past
		response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}