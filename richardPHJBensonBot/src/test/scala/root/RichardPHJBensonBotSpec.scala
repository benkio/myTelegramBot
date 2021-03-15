package root

import com.benkio.telegramBotInfrastructure.botCapabilities.ResourceSource
import org.scalatest._
import matchers.should._
import com.benkio.telegramBotInfrastructure.model.TextTrigger
import com.benkio.telegramBotInfrastructure.model.MediaFile
import org.scalatest.wordspec.AnyWordSpec

class RichardPHJBensonBotSpec extends AnyWordSpec with Matchers {

  def testFilename(filename: String): Assertion =
    if (ResourceSource
          .selectResourceAccess(RichardPHJBensonBot.resourceSource)
          .getResourceByteArray(filename)
          .isEmpty)
      fail(s"$filename cannot be found")
    else succeed

  "messageRepliesAudioData" should {
    "never raise an exception" when {
      "try to open the file in resounces" in {
        RichardPHJBensonBot.messageRepliesAudioData
          .flatMap(_.mediafiles)
          .foreach((mp3: MediaFile) => testFilename(mp3.filename))
      }
    }
  }

  "messageRepliesGifsData" should {
    "never raise an exception" when {
      "try to open the file in resounces" in {
        RichardPHJBensonBot.messageRepliesGifsData
          .flatMap(_.mediafiles)
          .foreach((gif: MediaFile) => testFilename(gif.filename))
      }
    }
  }

  "messageRepliesSpecialData" should {
    "never raise an exception" when {
      "try to open the file in resounces" in {
        for {
          rb <- RichardPHJBensonBot.messageRepliesSpecialData
          f1 <- rb.mediafiles
        } yield {
          testFilename(f1.filename)
        }

      }
    }
  }

  "commandRepliesData" should {
    "return a list of all triggers" when {
      "called" in {
        RichardPHJBensonBot.commandRepliesData.length shouldEqual 4
        RichardPHJBensonBot.messageRepliesData
          .flatMap(
            _.trigger match {
              case TextTrigger(lt) => lt
              case _               => ""
            }
          )
          .forall(s =>
            RichardPHJBensonBot.commandRepliesData
              .filter(_.trigger.command != "bensonify")
              .flatMap(_.text.text(null))
              .contains(s)
          )

        // commandRepliesData
        //   .last
        //   .text.text(Message(
        //     messageId = 0,
        //     date = 0,
        //     chat = Chat(id = 0, `type` = ChatType.Private)
        //   )) shouldEqual List("E PAAAARRRRRLAAAAAAAAA!!!!")
      }
    }
  }
}
