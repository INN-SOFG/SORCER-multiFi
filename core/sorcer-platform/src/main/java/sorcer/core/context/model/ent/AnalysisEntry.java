/*
 * Copyright 2013 the original author or authors.
 * Copyright 2013 SorcerSoft.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorcer.core.context.model.ent;

import sorcer.core.context.model.Analysis;
import sorcer.core.signature.ObjectSignature;
import sorcer.service.*;
import sorcer.service.ContextDomain;
import sorcer.service.modeling.Functionality;

/**
 * Created by Mike Soblewski on 06/03/16.
 */
public class AnalysisEntry extends Entry<Analysis> implements Analysis {

    private static final long serialVersionUID = 1L;

    private Contextion contextion;

    private Signature signature;

    public AnalysisEntry(String name, Analysis mda)  {
        this.key = name;
        this.impl = mda;
        this.type = Functionality.Type.MDA;
    }

    public AnalysisEntry(String name, Signature signature) {
        this.key = name;
        this.signature = signature;
        this.type = Functionality.Type.MDA;
    }

    public AnalysisEntry(String name, Analysis mda, Context context) {
        this.key = name;
        scope = context;
        this.impl = mda;
        this.type = Functionality.Type.MDA;
    }

    public Analysis getMda() {
        return out;
    }

    public Contextion getContextion() {
        return contextion;
    }

    public void setContextion(Contextion contextion) {
        this.contextion = contextion;
    }

    public Signature getSignature() {
        return signature;
    }

    @Override
    public void analyze(Request request, Context context) throws EvaluationException {
        try {
            if (impl != null && impl instanceof Analysis) {
                if (contextion == null) {
                    ((Analysis) impl).analyze(request, context);
                } else {
                    ((Analysis) impl).analyze(contextion, context);
                }
            } else if (signature != null) {
                impl = ((ObjectSignature)signature).initInstance();
                ((Analysis)impl).analyze(request, context);
            } else if (impl == null) {
                throw new InvocationException("No MDA analysis available!");
            }
        } catch (ContextException | SignatureException e) {
            throw new EvaluationException(e);
        }
    }
}