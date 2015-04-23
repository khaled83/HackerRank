package com.indeed.khaledabbas.bits;

import java.util.*;

public class BitSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 0000 0000 :2 words 7654 3210 :bitIndx 1 0 :wordInx = bitIndx/4 = bitIndx
	 * >> 2 0100 110 01111
	 */

	private long[] words;
	private int wordCount = 0;

	private static final int ADDRESS_BITS_PER_WORD = 6;
	private static final int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
	private static final long MASK_ONES = (1 << BITS_PER_WORD) - 1;

	private void set(int bitIndx) {
		rangeCheck(bitIndx);
		int wordIndx = wordIndx(bitIndx);
		ensureCapacity(wordIndx);
		words[wordIndx] |= (1L << bitIndx);
	}

	private boolean get(int bitIndx) {
		rangeCheck(bitIndx);
		int wordIndx = wordIndx(bitIndx);
		return (wordIndx <= wordCount)
				&& ((words[wordIndx] & (1L << bitIndx)) != 0);
	}

	private void flip(int bitIndx) {
		rangeCheck(bitIndx);
		int wordIndx = wordIndx(bitIndx);
		ensureCapacity(wordIndx);
		words[wordIndx] ^= (1L << bitIndx);
	}

	public void clear(int bitIndx) {
		rangeCheck(bitIndx);
		int wordIndx = wordIndx(bitIndx);
		ensureCapacity(wordIndx);
		words[wordIndx] &= ~(1 << bitIndx);
	}

	public int nextClearBit(int fromIndx) {
		int fromWordIndx = wordIndx(fromIndx);
		int result = 0;
		for (int wordIndx = wordCount - 1; wordIndx >= fromWordIndx; wordIndx--) {
			long word = words[wordIndx];
			int n = 0;
			if (word == 0) {
				n = 64;
				continue;
			} else {
				if (word <= 0x00000000FFFFFFFF) {
					n += 32;
					word = word << 32;
				}
				if (word <= 0x0000FFFFFFFFFFFFL) {
					n += 16;
					word <<= 16;
				}
				if (word <= 0x00FFFFFFFFFFFFFFL) {
					n += 8;
					word <<= 8;
				}
				if (word <= 0x0FFFFFFFFFFFFFFFL) {
					n += 4;
					word <<= 4;
				}
				if (word <= 0x3FFFFFFFFFFFFFFFL) {
					n += 2;
					word <<= 2;
				}
				if (word <= 0x7FFFFFFFFFFFFFFFL) {
					n += 1;
				}

			}
			result += n;
		}

		return result;
	}

	/**
	 * 0110 0000 :org 0000 :<<4 1000 :<<2 0000 :<<1
	 */
	public int nextSetBit(int fromIndx) {
		int result = 0;
		int fromWordIndx = wordIndx(fromIndx);
		for (int wordIndx = wordCount - 1; wordIndx > fromWordIndx; wordIndx--) {
			int n = 0;
			long word = words[wordIndx];
			if (word == 0) {
				n += BITS_PER_WORD;
				continue;
			} else {
				if (word << 32 > 0) {
					n += 32;
					word = word << 32;
				}
				if (word << 16 > 0) {
					n += 16;
					word = word << 16;
				}
				if (word << 8 > 0) {
					n += 8;
					word = word << 8;
				}
				if (word << 4 > 0) {
					n += 4;
					word = word << 4;
				}
				if (word << 2 > 0) {
					n += 2;
					word = word << 2;
				}
				if (word > 0) {
					n += 1;
				}
			}
		}
		return result;
	}

	private int wordIndx(int bitIndx) {
		return bitIndx >> ADDRESS_BITS_PER_WORD;
	}

	private void ensureCapacity(int wordIndx) {
		int wordsRequired = wordIndx + 1;
		if (wordsRequired > wordCount) {
			wordsRequired = Math.max(wordCount + (wordCount >> 1),
					wordsRequired);
			words = Arrays.copyOf(words, wordsRequired);
		}
	}

	private void rangeCheck(int bitIndx) throws IndexOutOfBoundsException {
		if (bitIndx < 0)
			throw new IndexOutOfBoundsException("Bit index negative: "
					+ bitIndx);
	}

}
