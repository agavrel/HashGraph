
/*
 * This file is public domain.
 *
 * SWIRLDS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF 
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SWIRLDS SHALL NOT BE LIABLE FOR 
 * ANY DAMAGES SUFFERED AS A RESULT OF USING, MODIFYING OR 
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

import java.io.IOException;
import java.time.Instant;

import com.swirlds.platform.Address;
import com.swirlds.platform.AddressBook;
import com.swirlds.platform.FCDataInputStream;
import com.swirlds.platform.FCDataOutputStream;
import com.swirlds.platform.FastCopyable;
import com.swirlds.platform.Platform;
import com.swirlds.platform.SwirldState;

/**
 * This demo collects statistics on the running of the network and consensus systems. It writes them to the
 * screen, and also saves them to disk in a comma separated value (.csv) file. Each transaction is 100
 * random bytes. So StatsDemoState.handleTransaction doesn't actually do anything.
 */
public class StatsDemoState implements SwirldState {
	/** the address book passed in by the Platform at the start */
	private AddressBook addressBook;

	@Override
	public synchronized AddressBook getAddressBookCopy() {
		return addressBook.copy();
	}

	@Override
	public synchronized FastCopyable copy() {
		StatsDemoState copy = new StatsDemoState();
		copy.copyFrom(this);
		return copy;
	}

	@Override
	public void copyTo(FCDataOutputStream outStream) throws IOException {
		addressBook.copyTo(outStream);
	}

	@Override
	public void copyFrom(FCDataInputStream inStream) throws IOException {
		addressBook.copyFrom(inStream);
	}

	@Override
	public synchronized void copyFrom(SwirldState old) {
		addressBook = ((StatsDemoState) old).addressBook.copy();
	}

	@Override
	public synchronized void handleTransaction(long id, boolean consensus,
			Instant timeCreated, byte[] transaction, Address address) {
	}

	@Override
	public void noMoreTransactions() {
	}

	@Override
	public synchronized void init(Platform platform, AddressBook addressBook) {
		this.addressBook = addressBook;
	}
}