package org.mysociety.mockyvote.yournextmp

import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.mockito.AdditionalAnswers
import org.mockito.invocation.InvocationOnMock
import org.mysociety.mockyvote.Constituency
import org.mysociety.mockyvote.ConstituencyRepository

import static org.mockito.Matchers.any
import static org.mockito.Matchers.anyMap
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class YourNextMpVoteServiceTest {

    def constituencyResponseMap = [:]

    def candidateResponseMap = [:]

    @Before
    void setUp() {
        def resourceDir = new File(this.class.getResource(this.class.simpleName).toURI())
        JsonSlurper jsonSlurper = new JsonSlurper()

        def constituencyResponse = new Expando()
        constituencyResponse.data = jsonSlurper.parse(new File(resourceDir, 'SW1A1AA.json'))
        constituencyResponse.status = 200
        constituencyResponseMap['SW1A1AA'] = constituencyResponse

        def candidateResponse = new Expando()
        candidateResponse.data = jsonSlurper.parse(new File(resourceDir, '65759.json'))
        candidateResponse.status = 200
        candidateResponseMap['65759'] = candidateResponse
    }

    @Test
    void "Retrieve constituency successfully"() {
        def mockMapitRest = mock(RESTClient)
        when(mockMapitRest.get(anyMap())).thenAnswer { InvocationOnMock invocationOnMock ->
            Map<String, String> argsMap = invocationOnMock.arguments[0] as Map<String, String>
            if (argsMap['path'].contains('postcode')) {
                return constituencyResponseMap['SW1A1AA']
            }
        }

        def mockYournextmpRest = mock(RESTClient)
        when(mockYournextmpRest.get(anyMap())).thenAnswer { InvocationOnMock invocationOnMock ->
            Map<String, String> argsMap = invocationOnMock.arguments[0] as Map<String, String>
            if (argsMap['path'].contains('membership')) {
                return candidateResponseMap['65759']
            }
        }

        def mockConstituencyRepository = mock(ConstituencyRepository)
        when(mockConstituencyRepository.save(any(Constituency))).thenAnswer(AdditionalAnswers.returnsFirstArg())

        def service = new YourNextMpVoteService(mapitRest: mockMapitRest, yournextmpRest: mockYournextmpRest, constituencyRepository: mockConstituencyRepository)
        def constituency = service.getConstituency('SW1A1AA')
        assert constituency.name == 'Cities of London and Westminster'
        assert constituency.country == 'England'
        assert constituency.candidates.size() == 2
        // TODO verify names
    }

}